package com.example.tanampohon

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
import kotlinx.android.synthetic.main.activity_donasi.*
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject

class DonasiActivity : AppCompatActivity() {

    private val kode = (0..999999).random().toString()
    private val hrg = 10000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donasi)
        val bundle = intent.extras
        val id_penumpang = bundle?.get("id_akun").toString()
        val jml_pohN = bundle?.get("jml_pohon").toString().toInt()

        val total = (jml_pohN * hrg).toString().toInt()

        txt_total.setText(total.toString())


//        btn_donasi.setOnClickListener() {
//            create()
//            val intent = Intent(this, TerimakasihActivity::class.java)
//            intent.putExtra("id_akun",id_penumpang)
//            startActivity(intent)
//        }

       spinner_metode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                tv_bankkk.text = getResources().getStringArray(R.array.metode_bayar)[i]
            }
            override fun onNothingSelected(adapterView: AdapterView<*>){}
       }

        btn_donasi.setOnClickListener{
            create()
            val bundle = intent.extras
            val id_penum = bundle?.get("id_akun").toString()
            intent = Intent(this, TerimakasihActivity::class.java)
            intent.putExtra("id_akun", id_penum)
            startActivity(intent)
        }

        kd_byr.setText(kode)
    }
    private fun create(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()
        val bundle = intent.extras
        val id_an = bundle?.get("id_akun").toString()

        val Jumjml_pohN = bundle?.get("jml_pohon").toString().toInt()
        val Jumtotal = txt_total.getText().toString().toInt()
        val JumKodeBayar = kd_byr.getText().toString().toInt()
//        val Jumjml_pohN = Jumjml_pohN.toString().toInt()


//        val hari = bundle?.get("hari").toString()
//        val nup = jumlah_pohonyaa.text.toString().toInt()
//        val hari = (nup * hrg).toString()

        val metode = tv_bankkk.text.toString()

        println(JumKodeBayar.toString()+" "+id_an+" "+Jumjml_pohN+" "+Jumtotal+" "+metode)

        AndroidNetworking.post(ApiKoneksi.CREATE3)
            .addBodyParameter("id_donasi",JumKodeBayar.toString())
            .addBodyParameter("id_akun",id_an)
            .addBodyParameter("jml_pohon", Jumjml_pohN.toString())
            .addBodyParameter("jml_donasi",Jumtotal.toString())
            .addBodyParameter("metode",metode)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(applicationContext,response?.getString("message"), Toast.LENGTH_SHORT).show()
                    if(response?.getString("message")?.contains("successfully")!!){}
                }
                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
