package com.example.tryuserapp.presentation.pesanan

sealed class PesananSideEffects {
    data class ShowSnackBarMessage(val message: String) : PesananSideEffects()
}