package com.example.tryuserapp.presentation.katalis_screen

import com.example.tryuserapp.data.model.KatalisModel

data class KatalisScreenUiState(
    val isLoading: Boolean = false,
    val katalisList: List<KatalisModel> = emptyList(),
    val selectedKatalisList: ArrayList<SelectedKatalis> = arrayListOf(),
    val errorMessage: String? = null
)

data class SelectedKatalis(
    val idKatalis: String,
    var quantity: Int
)  {
    fun increaseQuantity() : Int = quantity++

    fun decreaseQuantity() : Int = if (quantity > 0) quantity-- else quantity
}