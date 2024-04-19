package com.example.tryuserapp.presentation.katalis_screen

import com.example.tryuserapp.logic.OrderAction

sealed class KatalisScreenEvent {
    data object GetKatalis : KatalisScreenEvent()

    data class ModifyOrder(
        val katalisId: String,
        val orderAction : OrderAction
    ) : KatalisScreenEvent()

}