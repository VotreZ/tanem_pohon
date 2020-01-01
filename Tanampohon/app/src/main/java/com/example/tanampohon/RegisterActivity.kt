package com.example.tanampohon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_registrasi.*

//class RegisterActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_registrasi)
//        btn_daftar.setOnClickListener() {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//}


import android.app.ProgressDialog
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
import android.util.Log
//import android.widget.EditText
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
//import kotlinx.android.synthetic.main.activity_pembayaran.*
//import kotlinx.android.synthetic.main.activity_registrasi.*
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrasi)

        btn_daftar.setOnClickListener{
            if (txt_nama.length() == 0 || txt_email.length() == 0 || txt_user.length() == 0 || txt_pass.length() == 0 ) {
                Toast.makeText(this, "Lengkapi Data", Toast.LENGTH_SHORT).show()
            }else {
                create()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun create(){
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

//        val id_ak = id_akn.text.toString()
        val nama_r = txt_nama.getText().toString()
        val email_r = txt_email.getText().toString()
        val user_r = txt_user.getText().toString()
        val password_r = txt_pass.getText().toString()

//        println(ktp_r+" "+nama_r+" "+notelp_r+" "+email_r+" "+username_r+" "+password_r)

        AndroidNetworking.post(ApiKoneksi.CREATE2)
//            .addBodyParameter("id_akun",id_ak)
            .addBodyParameter("nama",nama_r)
            .addBodyParameter("email",email_r)
            .addBodyParameter("username",user_r)
            .addBodyParameter("password",password_r)
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
