package com.example.tryuserapp.presentation.home_screen

import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.logic.OrderAction

sealed class HomeScreenEvent {
    data object GetKatalis : HomeScreenEvent()

    data class ModifyOrder(
        val katalisModel: KatalisModel,
        val orderAction : OrderAction
    ) : HomeScreenEvent()

}