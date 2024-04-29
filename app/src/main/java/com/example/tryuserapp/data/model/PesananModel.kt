package com.example.tryuserapp.data.model

data class PesananModel(
    var id_customer : String,
    var id_hotel : String,
    var id_kurir : String,
    var daftarKatalisPesanan : DaftarKatalis,
    var total_harga : Float,
    var transfer_proof_image_link : String,
    var status_pesanan : String,
    var waktu_pesanan_dibuat : String,
    var lokasiUser : String = "",
    var jarak_user_dan_hotel : Float = 0f,
    var id_pesanan : String? = null
)