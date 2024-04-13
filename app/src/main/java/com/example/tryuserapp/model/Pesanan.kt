package com.example.tryuserapp.model

import com.example.tryuserapp.logic.StatusPesanan

data class Pesanan(
    var id_pesanan : Int,
    var id_user : Int,
    var id_hotel : Int,
    var id_kurir : Int,
    var list_daftar_pesanan : DaftarPesanan,
    var total_harga : Float,
    var status_Pesanan : StatusPesanan ,
){
    fun GetUserById(){

    }
    fun GetHotelById(){

    }fun GetKurirById(){

    }
}
