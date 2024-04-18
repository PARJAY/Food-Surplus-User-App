package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.model.Pesanan

data class PesananState(
    val isLoading: Boolean = false,
    val transaksiListState: List<Pesanan> = emptyList(),
    val errorMessage: String? = null,
)