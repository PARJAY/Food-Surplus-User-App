package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.ui.component.GoogleButton
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ScreenLengkapiData(){
    var alamat by remember {
        mutableStateOf("")
    }
    var noTelp by remember {
        mutableStateOf(0)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Brown),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .height(420.dp)
                .width(250.dp)
                .background(backGroundScreen),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Lengkapi Data",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(60.dp))
        TextField(
            value = alamat ,
            onValueChange ={
                    newValue ->
                alamat = newValue
            } )
        }
        TextField(
            value = noTelp ,
            onValueChange ={
                    newValue ->
                noTelp = newValue
            } )
        }
    }
}