package com.example.tryuserapp.ui.screen

import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.PesananModel
import com.example.tryuserapp.data.retrofit.RetrofitInstance
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.presentation.pesanan.PesananViewModel
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.tools.FirebaseHelper.Companion.uploadImageToFirebaseStorage
import com.example.tryuserapp.tools.Utility.Companion.showToast
import com.example.tryuserapp.ui.component.DiantarAtauAmbil
import com.example.tryuserapp.ui.component.Pembayaran
import com.example.tryuserapp.ui.component.RingkasanPesanan
import com.example.tryuserapp.ui.component.Toggleableinfo
import com.example.tryuserapp.ui.theme.Brown
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Timestamp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ScreenCheckOut(
    onNavigateToHome : () -> Unit,
    onNavigateToScreen : (String) -> Unit,
    pesananViewModel: PesananViewModel,
    userData :UserData,
    selectedIdHotel : String,
    alamatByName : String,
    alamatByGeolocation : LatLng,
    alamatHotelByName : String,
    selectedKatalis: SnapshotStateList<SelectedKatalis>,
    radioButtons : SnapshotStateList<Toggleableinfo>
) {
    val context = LocalContext.current

    var selectedImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }

    var hotelToUserDistanceInMeter by remember { mutableFloatStateOf(0f) }

    val daftarKatalis by remember { mutableStateOf(DaftarKatalis()) }

    var ongkirPrice: Float
    var totalHarga = 0F

    if (alamatByGeolocation != LatLng(0.0,0.0) && alamatHotelByName != "")
        LaunchedEffect(key1 = hotelToUserDistanceInMeter != 0f) {
            Log.d("ScreenCheckOut", "Launched Effect Status -> Running")
            val apiService = RetrofitInstance.api
            CoroutineScope(Dispatchers.IO).launch {
                val response = apiService.getDirections(
                    "${alamatByGeolocation.latitude},${alamatByGeolocation.longitude}",
                    alamatHotelByName
                )

                Log.d("ScreenCheckOut", "response : $response")
                Log.d("ScreenCheckOut", "direction : ${response.routes[0].legs[0].distance?.text}")

                val parts = response.routes[0].legs[0].distance?.text?.split(" ")
                if (parts?.size != 2) return@launch

                val value = parts[0].toFloatOrNull() ?: return@launch
                val unit = parts[1]

                hotelToUserDistanceInMeter = when (unit) {
                    "m" -> value
                    "km" -> value * 1000f
                    else -> {
                        Log.d("ScreenCheckOut", "distance type not found : $unit")
                        0f
                    }
                }

                Log.d("ScreenCheckOut", "distance : $hotelToUserDistanceInMeter")
                Log.d("ScreenCheckOut", "ongkir price (Rp 1500 / 1 km ) : Rp. ${hotelToUserDistanceInMeter * 1.5}")

            }
        }

    LazyColumn (
        modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        item {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Check Out Pesanan",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Info Pesanan Anda :",
                style = TextStyle(fontWeight = FontWeight.W700)
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiantarAtauAmbil(
                onNavigateToScreen,
                alamatByName = alamatByName,
                radioButtons
            )

            Spacer(modifier = Modifier.height(10.dp))

            RingkasanPesanan(selectedKatalis, hotelToUserDistanceInMeter)

            Spacer(modifier = Modifier.height(10.dp))

            Pembayaran(
                selectedImageUri,
                onSelectImageUri = { selectedImageUri = it }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    Box (
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ) {
        Button (
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                contentColor = androidx.compose.ui.graphics.Color.White,
                containerColor = Brown
            ),
            onClick = {
                Log.d("ScreenCheckOut", "selected katalis : $selectedKatalis")
                selectedKatalis.forEach {
                    daftarKatalis.daftarKatalis += Pair(it.idKatalis, it.quantity)
                    totalHarga += (it.quantity * it.hargaKatalis)
                    Log.d("ScreenCheckOut", "daftarKatalis : ${daftarKatalis.daftarKatalis}")
                }

                if (alamatByGeolocation == LatLng(0.0,0.0) || alamatHotelByName == "") {
                    Log.d("ScreenCheckOut", "You should stop here")
                    showToast(context, "Masukkan Alamat Anda")
                }
                else if (selectedImageUri == Uri.EMPTY) showToast(context, "Masukkan Bukti Pembayaran")
                else {
                    uploadImageToFirebaseStorage(
                        userIdForFileReference = "User_${userData.userId}",
                        file = selectedImageUri,
                        onSuccess = { showToast(context, it) },
                        onError = { showToast(context, "$it") }
                    )

                    ongkirPrice = hotelToUserDistanceInMeter * 1.5F

                    pesananViewModel.createPesanan(
                        newPesananModel = PesananModel(
                            id_customer =  userData.userId,
                            id_hotel = selectedIdHotel,
                            id_kurir = "",
                            daftarKatalis = daftarKatalis.daftarKatalis,
                            total_harga = totalHarga + ongkirPrice,
                            transfer_proof_image_link = selectedImageUri.lastPathSegment.toString(),
                            StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN.toString(),
                            Timestamp.now(),
                            geolokasiTujuan = "${alamatByGeolocation.latitude},${alamatByGeolocation.longitude}",
                            alamatTujuan = alamatByName,
                            hotelToUserDistanceInMeter,
                            ongkir = ongkirPrice,
                            catatan = ""
                        )
                    )

                    selectedKatalis.forEach {
                        pesananViewModel.decrementStok(
                            selectedKatalisId = it.idKatalis,
                            stok = it.stokKatalis,
                            quantity = it.quantity
                        )
                    }
                    onNavigateToHome()
                }
            }
        ) {
            Text(text = "Buat Pesanan")
        }
    }
}

//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun ScreenCheckOutPreview() {
//    TryUserAppTheme {
//        Surface {
//            ScreenCheckOut(
//                onNavigateToHome = {},
//                onNavigateToScreen = {},
//                PesananViewModel(
//                    PesananRepositoryImpl(db =  FirebaseFirestore.getInstance()),
//                    KatalisRepositoryImpl(db = FirebaseFirestore.getInstance())
//                ),
//                UserData(),
//                alamatByName = (),
//                alamatByGeolocation = (0.0,0.0)
//            )
//        }
//    }
//}