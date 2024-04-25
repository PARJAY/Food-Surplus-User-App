package com.example.tryuserapp.presentation.pesanan

sealed class PesananListEvent {
    data class GetListPesanan(val idCustomer : String) : PesananListEvent()

}