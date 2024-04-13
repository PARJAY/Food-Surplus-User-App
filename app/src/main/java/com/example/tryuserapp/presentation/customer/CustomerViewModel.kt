package com.example.tryuserapp.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val customerRepository: CustomerRepository
): ViewModel() {
    private val _state = MutableStateFlow(CustomerState())

    private val _customer = customerRepository.getAllCustomer()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _customer) { state, customer ->
        state.copy(
            customerListState = customer
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CustomerState())

    fun onEvent(event: CustomerEvent) {
        when (event) {
            is CustomerEvent.DeleteCustomer -> {
                viewModelScope.launch {
                    customerRepository.deleteCustomer(event.customer)
                }
            }

            is CustomerEvent.UpdateCustomer -> {
                viewModelScope.launch {
                    customerRepository.updateCustomer(event.customer)
                }
            }

            is CustomerEvent.ReadCustomer -> {
                viewModelScope.launch {
                    customerRepository.readCustomer(event.customer)
                }
            }

            is CustomerEvent.CreateCustomer -> {
                viewModelScope.launch {
                    customerRepository.insertCustomer(event.customer)

                }
            }
        }
    }
}
