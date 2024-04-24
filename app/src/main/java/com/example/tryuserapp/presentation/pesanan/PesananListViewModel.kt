package com.example.tryuserapp.presentation.pesanan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.repository.HotelRepositoryImpl
import com.example.tryuserapp.data.repository.PesananListRepositoryImpl
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.presentation.home_screen.HomeScreenSideEffect
import com.example.tryuserapp.presentation.home_screen.HomeScreenUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PesananListViewModel(
    private val pesananListRepositoryImpl: PesananListRepositoryImpl
) : ViewModel() {

    private val _state: MutableStateFlow<PesananState> =
        MutableStateFlow(PesananState())
    val state: StateFlow<PesananState> = _state.asStateFlow()

    private val _effect: Channel<PesananSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(PesananListEvent.GetListPesanan)
    }

    private fun setEffect(builder: () -> PesananSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: PesananState) {
        _state.value = newState
    }

    fun onEvent(event: PesananListEvent) {
        when (event) {
            is PesananListEvent.GetListPesanan -> getPesananList()

//            is HomeScreenEvent.ModifyOrder -> {
//                modifyOrder(
//                    katalisId = event.katalisId,
//                    action = event.orderAction
//                )
//            }
        }
    }


    private fun getPesananList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            pesananListRepositoryImpl.getPesananList { firebaseResult ->
                if (firebaseResult is FirebaseResult.Failure) {
                    setState(_state.value.copy(isLoading = false))
                    Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                    setEffect {
                        PesananSideEffects.ShowSnackBarMessage(
                            firebaseResult.exception.message ?: "Error fetching users"
                        )
                    }
                } else if (firebaseResult is FirebaseResult.Success) {
                    Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                    _state.value =
                        _state.value.copy(isLoading = false, pesananListState = firebaseResult.data)
                    setEffect { PesananSideEffects.ShowSnackBarMessage(message = "User list data loaded successfully") }
                }
            }
        }
    }


}