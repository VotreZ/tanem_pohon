package com.example.tanampohon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_terimakasih.*

class TerimakasihActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terimakasih)

        btn_riwayatdonasi1.setOnClickListener() {
            val bundle = intent.extras
            val id_manusia = bundle?.get("id_akun").toString()
            intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("id_akun", id_manusia)
            startActivity(intent)
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
}
