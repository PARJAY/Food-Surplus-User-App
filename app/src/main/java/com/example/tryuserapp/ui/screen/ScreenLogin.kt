package com.example.tryuserapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.ui.component.GoogleButton
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ScreenLogin(){
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
        Text(text = "Login",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Selamat datang silahkan  Login dengan akun user anda")
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            GoogleButton()
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
}