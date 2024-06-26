package com.example.tryuserapp.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.repository.HotelRepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val homeRepositoryImpl: HotelRepositoryImpl
) :ViewModel() {

    private val _state: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<HomeScreenSideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(HomeScreenEvent.GetHotel)
    }

    private fun setEffect(builder: () -> HomeScreenSideEffect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: HomeScreenUiState) {
        _state.value = newState
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetHotel -> getHotel()

//            is HomeScreenEvent.ModifyOrder -> {
//                modifyOrder(
//                    katalisId = event.katalisId,
//                    action = event.orderAction
//                )
//            }
        }
    }

    private fun getHotel() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            homeRepositoryImpl.getHotelList { firebaseResult ->
                if (firebaseResult is FirebaseResult.Failure) {
                    setState(_state.value.copy(isLoading = false))
                    Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                    setEffect {
                        HomeScreenSideEffect.ShowSnackBarMessage(
                            firebaseResult.exception.message ?: "Error fetching users"
                        )
                    }
                } else if (firebaseResult is FirebaseResult.Success) {
                    Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                    _state.value =
                        _state.value.copy(isLoading = false, hotelList = firebaseResult.data)
                    setEffect { HomeScreenSideEffect.ShowSnackBarMessage(message = "User list data loaded successfully") }
                }
            }
        }
    }

}