package com.example.tryuserapp.data.model

import com.google.firebase.Timestamp

data class PesananModel(
    var id_customer : String,
    var id_hotel : String,
    var id_kurir : String,
    var daftarKatalis: Map<String, Int> = emptyMap(),
    var total_harga : Float,
    var transfer_proof_image_link : String,
    var status_pesanan : String,
    var waktu_pesanan_dibuat : Timestamp,
    var lokasiUser : String = "",
    var jarak_user_dan_hotel : Float = 0f,
    var id_pesanan : String? = null,
    var ongkir : Float = 0f
)