package com.example.tryuserapp.ui.screen

import android.service.autofill.UserData
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.R
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.pesanan.PesananListEvent
import com.example.tryuserapp.presentation.pesanan.PesananListViewModel
import com.example.tryuserapp.presentation.pesanan.PesananState
import com.example.tryuserapp.ui.component.CheckStatusPesanan
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun PesananAnda(
    pesananState: PesananState,
    pesananListViewModel: PesananListViewModel,
    onPesananScreenEvent: (PesananListEvent) -> Unit,
    onNavigateToScreen : (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(backGroundScreen),
    ) {
        item {
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
        }
        items(pesananState.pesananListState){pesanan ->
            Column (
                modifier = Modifier.padding(horizontal = 8.dp)
            ){
                CheckStatusPesanan(
                    StatusPhoto = R.drawable.otw,
                    statusPesanan = StatusPesanan.SEDANG_DIANTAR,
                    pesananModel = pesanan,
                    onPesananScreenEvent = onPesananScreenEvent,
                    pesananListViewModel = pesananListViewModel
                    )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenPesananAndaPreview() {
//    TryUserAppTheme {
//        Surface {
//            PesananAnda(
//                pesananState = PesananState(),
//                onNavigateToScreen = {},
//                onHomeScreenEvent = HomeScreenEvent
//            )
//        }
//    }
//}
