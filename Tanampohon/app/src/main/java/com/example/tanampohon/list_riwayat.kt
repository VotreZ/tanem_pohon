package com.example.tanampohon

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ArrayAdapter
import com.example.tanampohon.RiwayatActivity
import com.example.tanampohon.R

class list_riwayat (private val context: RiwayatActivity,
    private val kd_donasi: ArrayList<String>,
    private val kd_metode: ArrayList<String>,
    private val kd_pohon: ArrayList<String>,
    private val kd_total: ArrayList<String>) : ArrayAdapter<String>(context, R.layout.lv_riwayat, kd_donasi)
    {

        override fun getView(position: Int, view: View?, parent: ViewGroup): View {
            val inflater = context.layoutInflater
            val rowView = inflater.inflate(R.layout.lv_riwayat, null, true)

            val kodeDonasi = rowView.findViewById(R.id.tv_kd_donasi) as TextView
            val kodeMetode = rowView.findViewById(R.id.tv_kd_metode) as TextView
            val kodePohon = rowView.findViewById(R.id.tv_kd_pohon) as TextView
            val kodeTotal = rowView.findViewById(R.id.tv_kd_total) as TextView

            kodeDonasi.text = kd_donasi[position]
            kodeMetode.text = kd_metode[position]
            kodePohon.text = kd_pohon[position]
            kodeTotal.text = kd_total[position]

            return rowView
        }
    }