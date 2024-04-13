package com.example.tryuserapp.data.model

data class KatalisModel(
    val id: String = "",
    val idHotel: String = "",
    val namaKatalis: String = "",
    val imageLink: String = "",
    val stokKatalis: Float = 0f,
    val komposisi: List<String> = listOf(),
    val hargaAwal: Float = 0f,
    val hargaJual: Float = 0f,
    val porsiJual: String = "",
)