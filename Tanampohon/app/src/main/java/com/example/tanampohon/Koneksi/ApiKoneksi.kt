package com.example.tanampohon.Koneksi

class ApiKoneksi {
    companion object {
        private val SERVER = "http://192.168.1.6/tanam_pohon/"
        val READ_log  = SERVER + "read_login.php"
        val READ_AKN = SERVER + "read_akun.php"
        val READ_DON = SERVER + "read_donasi.php"

        val CREATE2 = SERVER + "create_akun.php"
        val CREATE3 = SERVER + "create_donasi.php"

        val DELETE = SERVER + "delet_donasi.php"

        val UPDATE = SERVER + "update_akun.php"
    }
}