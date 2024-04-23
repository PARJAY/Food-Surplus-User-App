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
import com.example.tryuserapp.R
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.ui.component.CheckStatusPesanan
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun PesananAnda(onNavigateToScreen : (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                CheckStatusPesanan(R.drawable.otw, StatusPesanan.SEDANG_DIANTAR)
                Spacer(modifier = Modifier.height(8.dp))
                CheckStatusPesanan(R.drawable.sedang_dipesan, StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN)
                Spacer(modifier = Modifier.height(8.dp))
                CheckStatusPesanan(R.drawable.sudah_sampai, StatusPesanan.PESANAN_SAMPAI)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPesananAndaPreview() {
    TryUserAppTheme {
        Surface {
            PesananAnda(onNavigateToScreen = {})
        }
    }
}
