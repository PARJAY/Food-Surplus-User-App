package com.example.tryuserapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.screen.findHotelToYayasanDistance
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.White

@Composable
fun RingkasanPesanan(
    selectedKatalis: SnapshotStateList<SelectedKatalis>,
    hotelToUserDistance : Float,
    hotelToYayasanDistanceInMeter : Float
) {
    var totalHarga = 0F

    selectedKatalis.forEach { totalHarga += (it.hargaKatalis * it.quantity) }

    var ongkirPrice : Float? = 0f

    Button(
        modifier = Modifier
            .wrapContentSize(),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = White
        ),
        shape = RoundedCornerShape(16.dp)
    ){
        Column(modifier = Modifier.padding(top = 10.dp)){
            Text(
                text = "Ringkasan Pesanan",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

            )
            Spacer(modifier = Modifier.size(width = 0.dp, height = 8.dp))

            selectedKatalis.forEach {
                LeftRightText(
                    leftTextInfo = it.namaKatalis + " x " +  it.quantity,
                    rightTextPrice = it.hargaKatalis* it.quantity
                )
            }

            if (hotelToUserDistance != 0f) {
                ongkirPrice = hotelToUserDistance.times(1.5f)

                LeftRightText(
                    leftTextInfo = "Biaya transportasi",
                    rightTextPrice = ongkirPrice!!
                )

                Spacer(modifier = Modifier.height(16.dp))

                LeftRightText(
                    leftTextInfo = "Total Harga",
                    rightTextPrice = totalHarga + ongkirPrice!!
                )
            }
            else if (hotelToYayasanDistanceInMeter != 0f) {
                ongkirPrice = hotelToYayasanDistanceInMeter.times(1.5f)

                LeftRightText(
                    leftTextInfo = "Biaya transportasi",
                    rightTextPrice = ongkirPrice!!
                )

                Spacer(modifier = Modifier.height(16.dp))

                LeftRightText(
                    leftTextInfo = "Total Harga",
                    rightTextPrice = totalHarga + ongkirPrice!!
                )
            }
            else {
                Spacer(modifier = Modifier.height(16.dp))

                LeftRightText(
                    leftTextInfo = "Biaya transportasi",
                    rightTextPrice = ongkirPrice!!
                )

                Spacer(modifier = Modifier.height(16.dp))
                Box (modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(White)
                )
                LeftRightText(
                    leftTextInfo = "Total Harga",
                    rightTextPrice = totalHarga + ongkirPrice!!
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun RingkasanPesananPreview(){
//    TryUserAppTheme {
//        Surface {
//            RingkasanPesanan(selectedKatalis = SnapshotStateList<SelectedKatalis>)
//        }
//    }
//}