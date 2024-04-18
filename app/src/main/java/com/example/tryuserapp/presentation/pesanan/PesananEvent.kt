package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.data.model.Pesanan

sealed interface PesananEvent {
    data class CreatePesanan(val pesanan: Pesanan): PesananEvent
//    data class ReadPesanan(val pesanan: Pesanan): PesananEvent

}