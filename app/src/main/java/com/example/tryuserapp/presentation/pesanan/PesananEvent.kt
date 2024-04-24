package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent

sealed interface PesananEvent {
    data class CreatePesanan(val pesananModel: PesananModel): PesananEvent
    data class ReadPesanan(val pesanan: String): PesananEvent
//    data class ReadPesanan(val pesanan: Pesanan): PesananEvent

}
