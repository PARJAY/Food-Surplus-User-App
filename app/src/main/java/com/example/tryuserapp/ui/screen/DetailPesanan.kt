package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun DetailPesanan(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        Row{
            Image(
                painter = painterResource(id = R.drawable.back)
                , contentDescription = "Back")
            }
        }
    }


//Button(onClick = {navController.navigate("HomeScreen") }) {
    //                Text(text = "Back")
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailPesananPreview() {
    TryUserAppTheme {
        Surface {
            DetailPesanan(navController = rememberNavController())
        }
    }
}