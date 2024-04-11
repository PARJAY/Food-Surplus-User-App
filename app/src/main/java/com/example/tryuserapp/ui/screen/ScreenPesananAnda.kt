package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.ui.component.StatusPesanan
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun PesananAnda(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.navigate("HomeScreen") },
                painter = painterResource(id = R.drawable.back), contentDescription = "Back"
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pesanan Anda",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column (
                modifier = Modifier.padding(horizontal = 8.dp)
            ){
                StatusPesanan(R.drawable.otw, StatusPesanan.SEDANG_DIANTAR)
                Spacer(modifier = Modifier.height(8.dp))
                StatusPesanan(R.drawable.sedang_dipesan, StatusPesanan.SUDAH_DIPESAN)
                Spacer(modifier = Modifier.height(8.dp))
                StatusPesanan(R.drawable.sudah_sampai, StatusPesanan.SUDAH_SAMPAI)
            }
        }
    }
}

//
//Text(text = "Screen Pesanan anda")
//Button(onClick = { navController.navigate("HomeScreen") }) {
//    Text(text = "Back")
//}
//Button(onClick = {}) {
//    Text(text = "Konfirmasi Pesanan")
//}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPesananAndaPreview() {
    TryUserAppTheme {
        Surface {
            PesananAnda(navController = rememberNavController())
        }
    }
}
