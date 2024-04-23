package com.example.tryuserapp.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.data.model.CustomerModel
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import com.example.tryuserapp.presentation.pesanan.PesananSideEffects
import com.example.tryuserapp.presentation.pesanan.PesananState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent

class CustomerViewModel(
    private val customerRepository: CustomerRepositoryImpl,
    customerId: String
): ViewModel() {

    private val _state = MutableStateFlow(CustomerState())
    val state = _state.asStateFlow()
    private fun setState(newState: CustomerState) {
        _state.value = newState
    }

    private val _effect: Channel<PesananSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    private fun setEffect(builder: () -> PesananSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    init { onEvent(CustomerEvent.GetCustomerById(customerId)) }

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    // TODO : not yet implemented
                }
            }

            is CustomerEvent.UpdateCustomer -> {
                viewModelScope.launch {
                    customerRepository.addOrUpdateCustomer(event.customerId, event.customer)
                }
            }

            is CustomerEvent.GetCustomerById -> {
                viewModelScope.launch {
                    customerRepository.getCustomerById(event.customerId)
                }
            }

            is CustomerEvent.CreateCustomer -> {
                viewModelScope.launch {
                    customerRepository.addOrUpdateCustomer(event.customerId, event.customer)
                }
            }
        }
    }

    fun tambahDataCustomer(idCustomer: String, alamat : String, phoneNumber : String, name: String) {
        viewModelScope.launch {
            setState(_state.value.copy(isLoading = true))

            try {
                customerRepository.addOrUpdateCustomer(customerId = idCustomer, CustomerModel(address = alamat, phone_number = phoneNumber, id = idCustomer, name = name)  )
                setState(_state.value.copy(isLoading = false))
                setEffect { PesananSideEffects.ShowSnackBarMessage(message = "Tambah Catatan successfully") }
            } catch (e: Exception) {
                setState(_state.value.copy(isLoading = false, errorMessage = e.localizedMessage))
                setEffect { PesananSideEffects.ShowSnackBarMessage(e.message ?: "Tambah Catatan GAGAl ") }
                }
            }
        }

}