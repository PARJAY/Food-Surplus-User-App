package com.example.tryuserapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DiantarAtauAmbil(){
    Column {
        Row {
            Text(text = "Ambil Sendiri")
            Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
//            RadioButton(selected = , onClick = { /*TODO*/ })
        }
    }
}