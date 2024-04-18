package com.example.tryuserapp.model

import com.example.tryuserapp.logic.StatusPesanan

data class Pesanan(
    var id_pesanan : Int,
    var id_customer : Int,
    var id_hotel : Int,
    var id_kurir : Int,
    var list_daftar_katalis : DaftarKatalis,
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