package com.example.tryuserapp.data.model

import com.example.tryuserapp.logic.StatusHotel

data class HotelModel(
    var idHotel : String = "",
    var name : String = "",
    var phoneNumber : String = "",
    var email : String = "",
    var alamat : String = "",
    var listIdKatalis : List<String> = listOf(),
    var statusHotel : StatusHotel
){
    fun MendaftarkanHotel(){

    }
    fun MenambahkanBerkas(){

    }fun GetKatalisById(){

    }fun GetTransaksiById(){

    }
    fun InputInfoKatalis(){

    }
}