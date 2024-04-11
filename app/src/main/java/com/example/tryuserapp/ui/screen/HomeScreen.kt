package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.ui.component.ButtonKeranjangSmall
import com.example.tryuserapp.ui.component.ButtonPesananAnda
import com.example.tryuserapp.ui.component.InfoPesanan
import com.example.tryuserapp.ui.component.Katalis
import com.example.tryuserapp.ui.component.SearchBar
import com.example.tryuserapp.ui.component.TopBar
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun HomeScreen(navController: NavController){

    val scrollState = rememberScrollState()

    Box (
        Modifier
            .fillMaxSize()
            .background(backGroundScreen),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
        ) {
            TopBar(navController)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pesan Katalis",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SearchBar()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonPesananAnda(navController)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
               Column (
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 8.dp),
                   horizontalAlignment = Alignment.CenterHorizontally
               ){
                   Katalis(nameMakanan = "Ayam Goreng", navController)
                   Spacer(modifier = Modifier.height(5.dp))
                   Katalis(nameMakanan = "Mie Goreng", navController)
                   Spacer(modifier = Modifier.height(5.dp))
                   Katalis(nameMakanan = "Bakso Goreng", navController)
                   Spacer(modifier = Modifier.height(5.dp))
                   Katalis(nameMakanan = "Bakso Goreng", navController)
                   Spacer(modifier = Modifier.height(5.dp))
                   Katalis(nameMakanan = "Bakso Goreng", navController)
                   Spacer(modifier = Modifier.height(5.dp))

               }
            }
        }
       ButtonKeranjangSmall(navController)

    }
}











//        Text(text = "Home Screen")
//
//        Button(onClick = { navController.navigate("screenDetailPesanan") }) {
//            Text(text = "Detail Pesanan")
//        }
//        Button(onClick = { navController.navigate("screenProfile") }) {
//            Text(text = "Profile")
//        }
//        Button(onClick = { navController.navigate("screenPesananAnda") }) {
//            Text(text = "Pesanan Anda")
//        }
//        Button(onClick = { navController.navigate("screenCheckOut") }) {
//                Text(text = "Check Out")
//        }



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HomeScreenPreview() {
    TryUserAppTheme {
        Surface {
            HomeScreen(navController = rememberNavController())
        }
    }
}