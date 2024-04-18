package com.example.tryuserapp.data.model

import com.example.tryuserapp.logic.StatusPesanan

data class OrderModel (
    val idUser : String,
    val idHotel : String,
    val idKurir : String = "",
    val listDaftarKatalis : List<KatalisModel>,
    val totalHarga : Float,
    val transferProofImageLink : String,
    val statusPesanan : StatusPesanan,
    val waktuPesananDibuat : String
)