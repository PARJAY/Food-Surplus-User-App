package com.example.tryuserapp.data.model

import com.google.firebase.Timestamp

data class KatalisModel(
    val id: String = "",
    val idHotel: String = "",
    val namaKatalis: String = "",
    val imageLink: String = "",
    val stok: Int = 0,
    val komposisi: List<String> = listOf(),
    val hargaAwal: Float = 0f,
    val hargaJual: Float = 0f,
    val porsiJual: String = "",
    val kadaluarsa: Timestamp = Timestamp.now(),
    val statusKatalis: String = "",
)