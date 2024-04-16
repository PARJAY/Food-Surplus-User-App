package com.example.tryuserapp.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CustomerViewModel(private val customerRepository: CustomerRepositoryImpl): ViewModel() {

    private val _state = MutableStateFlow(CustomerState())
    val state = _state.asStateFlow()

    init { onEvent(CustomerEvent.ReadCustomer) }

    private fun setState(newState: CustomerState) {
        _state.value = newState
    }

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    // TODO : not yet implemented
                }
            }

            is CustomerEvent.UpdateCustomer -> {
                viewModelScope.launch {
                    customerRepository.updateCustomer(_state.value.customerState.id, event.customer)
                }
            }

            is CustomerEvent.ReadCustomer -> {
                viewModelScope.launch {
                    customerRepository.getCustomerById(_state.value.customerState.id)
                }
            }

            is CustomerEvent.CreateCustomer -> {
                viewModelScope.launch {
                    customerRepository.addCustomer(event.customer)
                }
            }
        }
    }
}