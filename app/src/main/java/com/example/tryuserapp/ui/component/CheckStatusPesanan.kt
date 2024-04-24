package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import com.example.tryuserapp.presentation.sign_in.UserData
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.presentation.pesanan.PesananListEvent
import com.example.tryuserapp.presentation.pesanan.PesananState
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun CheckStatusPesanan(
    pesananModel: PesananModel,
    StatusPhoto : Int,
    onPesananScreenEvent: (PesananListEvent) -> Unit,
    statusPesanan: StatusPesanan
){
    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .height(80.dp)
            .width(380.dp)
            .background(Orange)
            .padding(start = 16.dp, top = 4.dp),
    ) {
        Column (
                verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = painterResource(id = StatusPhoto), contentDescription = "Dalam Perjalanan" ,
                modifier = Modifier.size(40.dp))

        }
        Column(
            modifier = Modifier
                .padding(end = 0.dp)
        ) {
            Text(
                text = pesananModel.id_customer,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text = pesananModel.id_hotel,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text = pesananModel.status_pesanan,
//                when (statusPesanan) {
//                    StatusPesanan.SEDANG_DIANTAR-> "Sedang Diantarkan"
//                    StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN -> "Sedang Memesan"
//                    StatusPesanan.PESANAN_TERKONFIRMASI -> "Sedang Memesan"
//                    StatusPesanan.PESANAN_SAMPAI -> "Sedang Memesan"
//                    else -> "Unknown Error"
//                },
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.width(0.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalAlignment = Alignment.End
        ){
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 10.dp, bottom = 5.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Brown,
                    contentColor = Color.White
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Konfirmasi")
            }
        }
    }
}
//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun StatusPesananPreview(){
//    TryUserAppTheme {
//        Surface {
//            CheckStatusPesanan(R.drawable.otw, StatusPesanan.SEDANG_DIANTAR)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun StatusPesananPreview2(){
//    TryUserAppTheme {
//        Surface {
//            CheckStatusPesanan(R.drawable.sedang_dipesan, StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN)
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun StatusPesananPreview3(){
//    TryUserAppTheme {
//        Surface {
//            CheckStatusPesanan(R.drawable.sudah_sampai, StatusPesanan.PESANAN_SAMPAI)
//        }
//    }
//}