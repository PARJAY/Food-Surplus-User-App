package com.example.tryuserapp.presentation.home_screen

import com.example.tryuserapp.data.model.KatalisModel

data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val katalisList: List<KatalisModel> = emptyList(),
    val selectedKatalisList: ArrayList<SelectedKatalis> = arrayListOf(),
    val errorMessage: String? = null
)

data class SelectedKatalis(
    val idKatalis: String,
    var quantity: Int
)