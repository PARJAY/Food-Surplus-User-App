package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun StatusPesanan(StatusPhoto : Int, statusPesanan: StatusPesanan){
    Row(
        modifier = Modifier
            .height(80.dp)
            .width(380.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(start = 16.dp, top = 4.dp)
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
                .padding(end = 40.dp)
        ) {
            Text(
                text = "Nama",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text = "Hotel Megah",
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text =
                when (statusPesanan) {
                    StatusPesanan.SEDANG_DIANTAR-> "Sedang Diantarkan"
                    StatusPesanan.SUDAH_DIPESAN -> "Sedang Memesan"
                    else -> "Sudah Sampai!"
                },
                style = TextStyle(
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding( start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.width(0.dp))
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .padding(top = 10.dp, bottom = 5.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Konfirmasi")
            }
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun StatusPesananPreview(){
    TryUserAppTheme {
        Surface {
            StatusPesanan(R.drawable.otw, StatusPesanan.SEDANG_DIANTAR)
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun StatusPesananPreview2(){
    TryUserAppTheme {
        Surface {
            StatusPesanan(R.drawable.sedang_dipesan, StatusPesanan.SUDAH_DIPESAN)
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun StatusPesananPreview3(){
    TryUserAppTheme {
        Surface {
            StatusPesanan(R.drawable.sudah_sampai, StatusPesanan.SUDAH_SAMPAI)
        }
    }
}