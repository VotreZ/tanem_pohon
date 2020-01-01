package com.example.tanampohon

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.tanampohon.Koneksi.ApiKoneksi
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_riwayat.*
import org.json.JSONObject
import kotlin.system.exitProcess
import kotlin.system.exitProcess

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val id_penumpang = bundle?.get("id_akun").toString()
//        val bundle = intent.extras
//        val id_penumpang = bundle?.get("id_akun").toString().toInt()
//        jumlah_pohonyaa.text = getResources().getText(id_penumpang)


        btn_next.setOnClickListener() {
            val jml_pohonya = jumlah_pohonyaa.getText().toString()
            val jml = findViewById<EditText>(R.id.jumlah_pohonyaa)
            if (jml.length() == 0) {
                Toast.makeText(this, "Jumlah Pohon Harus Diisi", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, DonasiActivity::class.java)
                intent.putExtra("id_akun", id_penumpang)
                intent.putExtra("jml_pohon", jml_pohonya)
                startActivity(intent)

            }


        }
        btn_riwayat.setOnClickListener() {
            val intent = Intent(this, RiwayatActivity::class.java)
            intent.putExtra("id_akun", id_penumpang)
            startActivity(intent)
        }
        btn_akun.setOnClickListener() {
            //            val bundle = intent.extras
//            val id_penumpang = bundle?.get("id_akun").toString()
            val intent = Intent(this, AkunActivity::class.java)
            intent.putExtra("id_akun", id_penumpang)
//            val intent = Intent(this, AkunActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
      val buil = AlertDialog.Builder(this)
        buil.setTitle("Yakin Ingin Keluar Aplikasi ?")
        buil.setPositiveButton("Ya",{dialog: DialogInterface?, i: Int -> finishAffinity() })
        buil.setNegativeButton("Tidak",{dialog: DialogInterface?, i: Int -> })
        buil.show()
    }
}
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//
////        val bundle = intent.extras
////        val id_penumpang = bundle?.get("id_akun").toString()
////        intent = Intent(this, HomeActivity::class.java)
////        intent.putExtra("id_akun",id_penumpang)
////        startActivity(intent)
////        return true
//    }
//}

