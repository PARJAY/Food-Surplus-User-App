package com.example.tryuserapp.presentation.home_screen

import com.example.tryuserapp.logic.OrderAction

sealed class HomeScreenEvent {
    data object GetHotel : HomeScreenEvent()

}