package com.example.tryuserapp.presentation.pesanan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.model.Pesanan
import com.example.tryuserapp.presentation.customer.CustomerState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PesananViewModel(
    private val pesananRepository: PesananRepository
): ViewModel() {
    private val _state = MutableStateFlow(PesananState())

    private val _transaksi = pesananRepository.getAllPesanan()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _transaksi) { state, transaksi ->
        state.copy(
            transaksiListState = transaksi
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PesananState())

    private fun setState(newState: PesananState) {
        _state.value = newState
    }

    private val _effect: Channel<PesananSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    private fun setEffect(builder: () -> PesananSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    fun onEvent(event: PesananEvent) {
        when (event) {
//            is PesananEvent.ReadPesanan -> {
//                viewModelScope.launch {
//                    transaksiRepository.readTransaksi(event.pesanan)
//                }
//            }
//            is TransaksiEvent.CreateTransaksi -> {
//                viewModelScope.launch {
//                    transaksiRepository.insertTransaksi(event.transaksi)
//                }
//            }
            is PesananEvent.CreatePesanan -> createPesanan(event.pesanan)

        }
    }

     fun createPesanan(newPesanan: Pesanan) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                pesananRepository.insertTransaksi(newPesanan)
                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Transaksi added successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Error fetching users") }
            }
        }
    }
}