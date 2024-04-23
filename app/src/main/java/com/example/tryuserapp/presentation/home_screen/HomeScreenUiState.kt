package com.example.tryuserapp.presentation.home_screen

import com.example.tryuserapp.data.model.HotelModel
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis

data class HomeScreenUiState (
    val isLoading: Boolean = false,
    val hotelList: List<HotelModel> = emptyList(),
    val errorMessage: String? = null
)