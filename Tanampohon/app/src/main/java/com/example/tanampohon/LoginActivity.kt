package com.example.tanampohon

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

//class LoginActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        btn_register.setOnClickListener() {
//            val intent = Intent(this, RegisterActivity::class.java)
//            startActivity(intent)
//        }
//        btn_login.setOnClickListener(){
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//        }
//        }
//    }


import android.app.ProgressDialog
import android.content.DialogInterface
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import android.content.Intent as Intent1

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = "Login"

        btn_login.setOnClickListener{
            CekLogin()
            val bundle = intent.extras
            val userN = bundle?.get("username").toString()
            intent = Intent(this, AkunActivity::class.java)
            intent.putExtra("uername",userN)
        }
        btn_register.setOnClickListener(){
            intent = Intent1(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
    //==================================================================================================
    private fun CekLogin(){
        val loading = ProgressDialog(this)
        loading.setMessage("Memeriksa Data...")
        loading.show()

        val userr = ed_user.getText().toString()
        val passs = ed_pass.getText().toString()

        AndroidNetworking.post(ApiKoneksi.READ_log)
            .addBodyParameter("username",userr)
            .addBodyParameter("password",passs)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {

                    val jsonArray = response?.optJSONArray("result")
                    loading.dismiss()

                    for(i in 0 until jsonArray?.length()!!) {
                        val jsonObject = jsonArray?.optJSONObject(i)
                        val id_penumpang = jsonObject.getString("id_akun").toString()

                        if (jsonArray?.length()-1 == i){
                            tv_id_get.setText(id_penumpang)
                            val id_log = tv_id_get.text
                            intent = Intent1(this@LoginActivity, HomeActivity::class.java)
                            intent.putExtra("id_akun",id_log)
                            startActivity(intent)
                            Toast.makeText(this@LoginActivity, "Login Sukses", Toast.LENGTH_SHORT).show()
                        }
                    }
                    if(userr != ApiKoneksi.READ_log || passs != ApiKoneksi.READ_log){
                        Toast.makeText(this@LoginActivity, "Username atau Password Salah", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR",anError?.errorDetail?.toString())
                    Toast.makeText(applicationContext,"Connection Failure", Toast.LENGTH_SHORT).show()
                }
            })
    }
//==================================================================================================
override fun onBackPressed() {
    val buil = AlertDialog.Builder(this)
    buil.setTitle("Yakin Ingin Keluar Aplikasi ?")
    buil.setPositiveButton("Ya",{ dialog: DialogInterface?, i: Int -> finishAffinity() })
    buil.setNegativeButton("Tidak",{ dialog: DialogInterface?, i: Int -> })
    buil.show()
}
}
