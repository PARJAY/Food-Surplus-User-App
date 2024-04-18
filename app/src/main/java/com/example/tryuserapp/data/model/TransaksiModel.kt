package com.example.tryuserapp.data.model


data class TransaksiModel (
    val id_transaksi : Int = 0,
    val id_pesanan : Int = 0,
    val keuntungan : Float = 0f,
    val total_biaya_admin : Float = 0f,
    val waktu_transaksi_dibuat : String = "",
)