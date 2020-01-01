package com.example.tanampohon

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
import kotlinx.android.synthetic.main.activity_registrasi.*
import kotlinx.android.synthetic.main.activity_riwayat.*
import org.json.JSONObject

class RiwayatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)
        val bundle = intent.extras
//        val idManusia = bundle?.get("id_donasi").toString()
        val idManusia = bundle?.get("id_akun").toString()

        lv_riwayat.setOnItemClickListener{adapterView, view, position, id ->
            val loading = ProgressDialog(this)
            val builder = AlertDialog.Builder(this@RiwayatActivity)
            builder.setTitle("Konfirmasi")
            builder.setMessage("Hapus data?")
            builder.setPositiveButton("Ya"){dialog, which ->

                val itemAtPos = adapterView.getItemAtPosition(position)

                AndroidNetworking.post(ApiKoneksi.DELETE)
                    .addBodyParameter("id_donasi",itemAtPos.toString())
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

                intent = Intent(this, RiwayatActivity::class.java)
//                intent.putExtra("id_donasi",idManusia)
                intent.putExtra("id_akun",idManusia)
                startActivity(intent)
            }



            builder.setNegativeButton("Batal"){dialog,which ->}

            builder.setNeutralButton(""){_,_ ->

            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val bundle = intent.extras
        val id_penumpang = bundle?.get("id_akun").toString()

        intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("id_akun",id_penumpang)
        startActivity(intent)
        return true
    }

    override fun onResume(){
        super.onResume()
        TampilDonasi()
    }

    private fun TampilDonasi(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        val bundle = intent.extras
        val idAkun = bundle?.get("id_akun").toString()

        AndroidNetworking.post(ApiKoneksi.READ_DON)
            .addBodyParameter("id_akun",idAkun)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    val list_idDon = arrayListOf<String>()
                    val list_metode = arrayListOf<String>()
                    val list_pohon = arrayListOf<String>()
                    val list_total = arrayListOf<String>()

                    for(i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)

                        list_idDon.add((jsonObject.getString("id_donasi")).toString())
                        list_metode.add((jsonObject.getString("metode")).toString())
                        list_pohon.add((jsonObject.getString("jml_pohon")).toString())
                        list_total.add((jsonObject.getString("jml_donasi")).toString())

                        if (jsonArray?.length()-1 == i) {
                            loading.dismiss()
                            val adapter = list_riwayat(this@RiwayatActivity, list_idDon, list_metode, list_pohon, list_total)
                            lv_riwayat.adapter = adapter

                        }
                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }

}
