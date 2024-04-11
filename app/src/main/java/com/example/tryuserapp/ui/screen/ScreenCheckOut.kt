package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun ScreenCheckOut(navController: NavController){
    Column {
        Text(text = "Screen Check Out")
        Button(onClick = { navController.navigate("HomeScreen") }) {
            Text(text ="Back")
        }
        Button(onClick = { navController.navigate("screenPesananAnda") }) {
            Text(text ="Buat Pesanan")
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenCheckOutPreview() {
    TryUserAppTheme {
        Surface {
            ScreenCheckOut(navController = rememberNavController())
        }
    }
}
