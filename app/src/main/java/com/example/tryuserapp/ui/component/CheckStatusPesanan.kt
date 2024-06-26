package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.MyApp
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.pesanan.PesananListViewModel
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.ButtonDisable
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.HijauTua
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.PurpleGrey80
import com.example.tryuserapp.ui.theme.TextDisable
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.google.firebase.Timestamp

@Composable
fun CheckStatusPesanan(
    pesananModel: PesananModel,
    pesananListViewModel: PesananListViewModel,
    statusPhoto : Int,
    onNavigateToDetailPesananScreen : (pesananModel : PesananModel, desiredScreen : String) -> Unit
) {
    val waktuPesananDibuat = pesananModel.waktu_pesanan_dibuat

    val buttonDisabledColor = ButtonDefaults.buttonColors(
        containerColor = ButtonDisable,
        contentColor = TextDisable
    )

    val buttonClickableColor = ButtonDefaults.buttonColors(
        containerColor = HijauTua,
        contentColor = Color.White
    )

    Button(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        onClick = {
            onNavigateToDetailPesananScreen(
                pesananModel,
                Screen.ScreenDetailPesananAnda.route
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = statusPhoto),
                    contentDescription = "Dalam Perjalanan",
                    modifier = Modifier
                        .size(40.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Fit
                )

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text =
                    when (pesananModel.status_pesanan) {
                        StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN.toString() -> "Menunggu Konfirmasi"
                        StatusPesanan.TERKONFIRMASI_ADMIN.toString(), StatusPesanan.DIANTAR.toString() -> "Diantar"
                        StatusPesanan.SAMPAI.toString() -> "Pesanan Terkirim"
                        StatusPesanan.SELESAI.toString() -> "Pesanan Selasai"
                        else -> "Status Pesanan Error"
                    },
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding( start = 16.dp, bottom = 5.dp, top = 5.dp)
                )

                Text(
                    text = "${waktuPesananDibuat.toDate()}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding( start = 16.dp, bottom = 5.dp)
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .padding(top = 5.dp, bottom = 5.dp, start = 16.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = when (pesananModel.status_pesanan) {
                            StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN.toString()-> buttonDisabledColor
                            StatusPesanan.TERKONFIRMASI_ADMIN.toString()-> buttonDisabledColor
                            StatusPesanan.DIANTAR.toString()-> buttonDisabledColor
                            StatusPesanan.SELESAI.toString() -> buttonDisabledColor
                            StatusPesanan.SAMPAI.toString() -> buttonClickableColor
                            else -> buttonDisabledColor
                        },
                        onClick = {
                            Log.d("Id Pesanan", "${pesananModel.id_pesanan}")

                            pesananListViewModel.updateStatusPesanan(pesananModel.id_pesanan!!)
                        },
                        enabled = when (pesananModel.status_pesanan) {
                            StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN.toString(),
                            StatusPesanan.TERKONFIRMASI_ADMIN.toString(),
                            StatusPesanan.DIANTAR.toString(),
                            StatusPesanan.SELESAI.toString() -> false
                            StatusPesanan.SAMPAI.toString() -> true
                            else -> false
                        }
                    ) {
                        Text(text = "CONFIRM")
                    }
                }
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
            CheckStatusPesanan(
                PesananModel(
                    id_customer = "",
                    id_hotel = "",
                    id_kurir = "",
                    daftarKatalis = emptyMap(),
                    transfer_proof_image_link = "",
                    total_harga = 0f,
                    status_pesanan = StatusPesanan.DIANTAR.toString(),
                    waktu_pesanan_dibuat = Timestamp.now(),

                ),
                PesananListViewModel(
                    MyApp.appModule.pesananListRepositoryImpl,
                    idCustomer = ""
                ),
                statusPhoto = R.drawable.sedang_dipesan,
                onNavigateToDetailPesananScreen = { pesananModel, desiredScreen ->
                    
                }
            )
        }
    }
}

