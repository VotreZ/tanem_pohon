package com.example.tanampohon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_akun.*
import org.json.JSONObject
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
import android.app.ProgressDialog

class AkunActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_akun)
        btn_logout.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        TampilProfil()

        btn_edit.setOnClickListener(){
            update()
        }

//        btn_kembali.setOnClickListener{
////            intent = Intent(this, HomeActivity::class.java)
//            startActivity(Intent(this, HomeActivity::class.java))
//        }

    }

    private fun TampilProfil(){

        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()


        val bundle = intent.extras
        val id_akunn = bundle?.get("id_akun").toString()


        AndroidNetworking.post(ApiKoneksi.READ_AKN)
            .addBodyParameter("id_akun", id_akunn)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    val jsonObject = jsonArray?.optJSONObject(0)
                    val id_akunPro = jsonObject?.getString("id_akun").toString()
                    val namaPro = jsonObject?.getString("nama").toString()
                    val emailPro = jsonObject?.getString("email").toString()
                    val userPro = jsonObject?.getString("username").toString()
                    val passPro = jsonObject?.getString("password").toString()

                    loading.dismiss()
                    idd.setText(id_akunPro)
                    txt_nama2.setText(namaPro)
                    txt_email2.setText(emailPro)
                    txt_user2.setText(userPro)
                    txt_pass2.setText(passPro)

                    println(id_akunPro+" "+emailPro+" "+userPro+" "+passPro)
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
//
    private fun update(){
        val loading = ProgressDialog(this)
        loading.setMessage("Mengupdate data...")
        loading.show()

//        val bundle = intent.extras
//        val idUp = bundle?.get("id_akun").toString()


        val idU = idd.getText().toString()
        val namaU = txt_nama2.getText().toString()
        val emailU = txt_email2.getText().toString()
        val userU = txt_user2.getText().toString()
        val passU = txt_pass2.getText().toString()

        AndroidNetworking.post(ApiKoneksi.UPDATE)
            .addBodyParameter("id_akun", idU)
            .addBodyParameter("username",userU)
            .addBodyParameter("password",passU)
            .addBodyParameter("nama",namaU)
            .addBodyParameter("email",emailU)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        val bundle = intent.extras
        val id_penumpang = bundle?.get("id_akun").toString()

        intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("id_akun",id_penumpang)
        startActivity(intent)
        return true
    }

}
