package com.example.tryuserapp.model

data class Katalis(
    var id : Int,
    var id_hotel : Int,
    var nama : String,
    var gambar : String,
    var jumlah : Int,
    var komposisi : List<String>,
    var harga_pokok : Float,
    var harga_jual : Float,
){
    fun GetHotelById(){

    }
}
