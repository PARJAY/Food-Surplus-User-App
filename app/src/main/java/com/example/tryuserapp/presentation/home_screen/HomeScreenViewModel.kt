package com.example.tryuserapp.presentation.home_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.common.FirebaseResult
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.data.repository.KatalisRepositoryImpl
import com.example.tryuserapp.logic.OrderAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val katalisRepositoryImpl: KatalisRepositoryImpl) : ViewModel() {

    private val _state: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val state: StateFlow<HomeScreenUiState> = _state.asStateFlow()

    private val _effect: Channel<HomeScreenSideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    init { onEvent(HomeScreenEvent.GetKatalis) }

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
                    katalisModel= event.katalisModel,
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
                    setEffect { HomeScreenSideEffects.ShowSnackBarMessage(firebaseResult.exception.message ?: "Error fetching users") }
                }
                else if (firebaseResult is FirebaseResult.Success) {
                    Log.d("VIEWMODEL: ", "sucess - ${firebaseResult.data}")
                    _state.value = _state.value.copy(isLoading = false, katalisList = firebaseResult.data)
                    setEffect { HomeScreenSideEffects.ShowSnackBarMessage(message = "User list data loaded successfully") }
                }
            }
        }
    }

    private fun modifyOrder(katalisModel: KatalisModel, action : OrderAction) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true) // Update loading state

            val currentSelectedList = _state.value.selectedKatalisList
            val index = currentSelectedList.indexOfFirst { it.idKatalis == katalisModel.id }

            if (action == OrderAction.INCREMENT) {
                if (index == -1) currentSelectedList.add(SelectedKatalis(katalisModel.id, 1))
                else currentSelectedList[index].quantity++
            }
            else if (action == OrderAction.DECREMENT) {
                val selectedItem = currentSelectedList[index]

                if (selectedItem.quantity - 1 == 0) currentSelectedList.removeAt(index)
                else currentSelectedList[index].quantity--
            }

            // Update state with modified list
            _state.value = _state.value.copy(selectedKatalisList = currentSelectedList, isLoading = false)
        }
    }

    private fun getKatalisById(id: String) : KatalisModel {
        return KatalisModel()
    }
}