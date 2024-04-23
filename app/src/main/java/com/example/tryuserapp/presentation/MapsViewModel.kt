package com.example.tryuserapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tryuserapp.presentation.maps.MapsState

class MapsViewModel : ViewModel() {
    var state by mutableStateOf(MapsState())
}