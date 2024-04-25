package com.example.tryuserapp.presentation.pesanan

sealed class PesananListEvent {
    data object GetListPesanan : PesananListEvent()
    data class GetUserListPesanan(val idCustomer : String) : PesananListEvent()

}