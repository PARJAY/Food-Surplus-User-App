package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.Gray
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun ButtonPesananAnda(
    onNavigateToScreen : (String) -> Unit
) {
    Column {
        Button(
            onClick = {  onNavigateToScreen(Screen.ScreenPesananAnda.route)},
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(16.dp)
                )
                .width(170.dp)
                .height(35.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Brown,
                contentColor = Color.Black
            )
        ) {
            Text(
                text = "Lihat Pesanan Anda",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ButtonPesananAndaPreview(){
    TryUserAppTheme {
        Surface {
            ButtonPesananAnda(
                onNavigateToScreen = {}
            )
        }
    }
}