package com.example.tryuserapp.presentation.pesanan

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.repository.PesananListRepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PesananListViewModel(
    private val pesananListRepositoryImpl: PesananListRepositoryImpl,
    idCustomer : String
) : ViewModel() {

    private val _state: MutableStateFlow<PesananState> =
        MutableStateFlow(PesananState())
    val state: StateFlow<PesananState> = _state.asStateFlow()

    private val _effect: Channel<PesananSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        PesananListEvent.GetUserListPesanan (idCustomer)
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
            is PesananListEvent.GetListPesanan -> {

            }

//            is HomeScreenEvent.ModifyOrder -> {
//                modifyOrder(
//                    katalisId = event.katalisId,
//                    action = event.orderAction
//                )
//            }

            is PesananListEvent.GetUserListPesanan -> getPesananList(event.idCustomer)
        }
    }


    private fun getPesananList(idCustomer : String) {
        Log.d("VIEWMODEL ID CUSTOMER: ", "idCustomer - $idCustomer")

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            pesananListRepositoryImpl.getPesananList(
                callback =  { firebaseResult ->
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
                },
                idCustomer = idCustomer
            )
        }
    }


    fun updateStatusPesnaan(selectedPesananId : String ) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                pesananListRepositoryImpl.updateStatusPesanan(pesananId = selectedPesananId, fieldToUpdate = "status_pesanan", newValue = "SUDAH_SAMPAI"   )
                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Update Stok successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Error Stok ") }
            }
        }
    }


}