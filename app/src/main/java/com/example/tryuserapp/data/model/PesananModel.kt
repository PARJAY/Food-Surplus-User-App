package com.example.tryuserapp.data.model

data class PesananModel(
    var id_customer : String,
    var id_hotel : String,
    var id_kurir : String,
    var list_id_daftar_katalis : String,
    var total_harga : Float,
    var transfer_proof_image_link : String,
    var status_pesanan : String,
    var waktu_pesanan_dibuat : String,
    var id_pesanan : String? = ""
){
    fun getUserById(){

    }
    fun getKurirById(){

    }
    fun getHotelById(){

    }
}