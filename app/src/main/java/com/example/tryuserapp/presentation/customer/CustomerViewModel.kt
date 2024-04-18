package com.example.tryuserapp.presentation.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CustomerViewModel(
    private val customerRepository: CustomerRepositoryImpl,
    customerId: String
): ViewModel() {

    private val _state = MutableStateFlow(CustomerState())
    val state = _state.asStateFlow()

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
}