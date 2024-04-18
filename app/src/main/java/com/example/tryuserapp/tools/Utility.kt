package com.example.tryuserapp.tools

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.home_screen.SelectedKatalis
import kotlinx.coroutines.launch

class Utility {
    companion object {

        fun modifyOrder(
            selectedKatalisList: ArrayList<SelectedKatalis>,
            katalisId: String,
            action : OrderAction,
        ) {
            val existingKatalis = selectedKatalisList.firstOrNull { it.idKatalis == katalisId }

            if (action == OrderAction.INCREMENT) {
                if (existingKatalis == null) selectedKatalisList.add(SelectedKatalis(katalisId, 1))
                else existingKatalis.quantity++
            }

            if (action == OrderAction.DECREMENT) {
                if (existingKatalis == null) return
                if (existingKatalis.quantity - 1 == 0) selectedKatalisList.remove(existingKatalis)
                else existingKatalis.quantity--
            }

        }
    }
}