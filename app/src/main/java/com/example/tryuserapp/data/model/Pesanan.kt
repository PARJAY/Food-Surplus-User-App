package com.example.tryuserapp.data.model

import com.example.tryuserapp.logic.StatusPesanan

data class Pesanan(
    var id_customer : String,
    var id_hotel : String,
    var id_kurir : String,
    var list_id_daftar_katalis : String,
    var total_harga : Float,
    var transfer_proof_image_link : String,
    var status_pesanan : StatusPesanan,
    var waktu_pesanan_dibuat : String
){
    fun getUserById(){

    }
    fun getKurirById(){

    }
    fun getHotelById(){

    }
}