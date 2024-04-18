package com.example.tryuserapp.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.repository.CustomerRepositoryImpl
import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.logic.OrderAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val katalisRepositoryImpl: KatalisRepositoryImpl
) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<HomeScreenSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        onEvent(HomeScreenEvent.GetKatalis)
    }

    private fun setEffect(builder: () -> HomeScreenSideEffects) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    private fun setState(newState: HomeScreenUiState) {
        _state.value = newState
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetKatalis -> getKatalis()

            is HomeScreenEvent.ModifyOrder -> {
                modifyOrder(
                    katalisId = event.katalisId,
                    action = event.orderAction
                )
            }
        }
    }

    private fun getKatalis() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            katalisRepositoryImpl.getKatalisList { firebaseResult ->
                if (firebaseResult is FirebaseResult.Failure) {
                    setState(_state.value.copy(isLoading = false))
                    Log.d("VIEWMODEL: ", "error - ${firebaseResult.exception.message}")
                    setEffect {
                        HomeScreenSideEffects.ShowSnackBarMessage(
                            firebaseResult.exception.message ?: "Error fetching users"
                        )
                    }
                } else if (firebaseResult is FirebaseResult.Success) {
                    Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                    _state.value =
                        _state.value.copy(isLoading = false, katalisList = firebaseResult.data)
                    setEffect { HomeScreenSideEffects.ShowSnackBarMessage(message = "User list data loaded successfully") }
                }
            }
        }
    }

        // (adjust code) find if exist
        // (adjust code) if not exist make a new data class
        // if increment perform ++ action
        // if decrement perform -- action and if quantity == 0, dont add data
        private fun modifyOrder(katalisId: String, action: OrderAction) {
            viewModelScope.launch {
                _state.value = _state.value.copy(isLoading = true) // Update loading state

                val selectedKatalisList = _state.value.selectedKatalisList
                val existingKatalis = selectedKatalisList.firstOrNull { it.idKatalis == katalisId }

                Log.d("VIEWMODEL", "existingKatalis = $existingKatalis")

                if (action == OrderAction.INCREMENT) {
                    Log.d("VIEWMODEL", "user action = INCREMENT")
                    if (existingKatalis == null) {
                        selectedKatalisList.add(SelectedKatalis(katalisId, 1))
                        Log.d(
                            "VIEWMODEL",
                            "no existingKatalis with id($katalisId) found " +
                                    "adding SelectedKatalis (${SelectedKatalis(katalisId, 1)})" +
                                    " to selectedKatalisList (${selectedKatalisList})"
                        )
                    } else {
                        existingKatalis.quantity++
                        Log.d(
                            "VIEWMODEL",
                            "existingKatalis with id($katalisId) found! " +
                                    "increasing quantity of (${existingKatalis}) " +
                                    "to (${existingKatalis.quantity})"
                        )
                    }
                }

                if (action == OrderAction.DECREMENT) {
                    Log.d("VIEWMODEL", "user action = DECREMENT")
                    if (existingKatalis == null) {
                        Log.d(
                            "VIEWMODEL", "no existingKatalis with id($katalisId) found " +
                                    "no action performed since nothing to decrement"
                        )
                        return@launch
                    }
                    if (existingKatalis.quantity - 1 == 0) {
                        Log.d(
                            "VIEWMODEL", "existingKatalis with id($katalisId) found " +
                                    "quantity (${existingKatalis}) decreased to 0 that mean " +
                                    "remove the (${existingKatalis}) from (${selectedKatalisList})"
                        )
                        selectedKatalisList.remove(existingKatalis)
                    } else {
                        Log.d(
                            "VIEWMODEL",
                            "existingKatalis with id($katalisId) found! " +
                                    "decreasing quantity of (${existingKatalis}) " +
                                    "to (${existingKatalis.quantity})"
                        )
                        existingKatalis.quantity--
                    }

                }

                // no debug mode
//            if (action == OrderAction.INCREMENT) {
//                if (existingKatalis == null) selectedKatalisList.add(SelectedKatalis(katalisId, 1))
//                else existingKatalis.quantity++
//            }
//
//            if (action == OrderAction.DECREMENT) {
//                if (existingKatalis == null) return@launch
//                if (existingKatalis.quantity - 1 == 0) selectedKatalisList.remove(existingKatalis)
//                else existingKatalis.quantity--
//            }
//            // Update state with modified list
//            _state.value = _state.value.copy(selectedKatalisList = selectedKatalisList, isLoading = false)
            }
        }

        private fun getKatalisById(id: String): KatalisModel? =
            _state.value.katalisList.find { it.id == id }
    }

