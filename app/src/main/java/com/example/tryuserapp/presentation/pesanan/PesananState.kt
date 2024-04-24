package com.example.tryuserapp.presentation.pesanan

import com.example.tryuserapp.data.model.PesananModel

data class PesananState(
    val isLoading: Boolean = false,
    val pesananListState: List<PesananModel> = emptyList(),
    val errorMessage: String? = null,
)