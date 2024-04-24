package com.example.tryuserapp.ui.screen

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.data.model.DaftarKatalis
import com.example.tryuserapp.data.model.Pesanan
import com.example.tryuserapp.logic.StatusPesanan
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.presentation.pesanan.PesananViewModel
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.tools.FirebaseHelper.Companion.uploadImageToFirebaseStorage
import com.example.tryuserapp.ui.component.DiantarAtauAmbil
import com.example.tryuserapp.ui.component.Pembayaran
import com.example.tryuserapp.ui.component.RingkasanPesanan
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.backGroundScreen
import com.google.android.gms.maps.model.LatLng
import java.util.Calendar

@Composable
fun ScreenCheckOut(
    onNavigateToHome : () -> Unit,
    onNavigateToScreen : (String) -> Unit,
    pesananViewModel: PesananViewModel,
    userData :UserData,
    alamatByName : String,
    alamatByGeolocation : LatLng,
    selectedKatalis: SnapshotStateList<SelectedKatalis>
){
    val context = LocalContext.current


    var selectedImageUri by remember {
        mutableStateOf<Uri?>(Uri.EMPTY)
    }
LazyColumn {
    item() {
        Box (
            Modifier
                .fillMaxSize()
                .height(10000.dp)
                .background(backGroundScreen),
            contentAlignment = Alignment.TopCenter
        ){
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(bottom = 32.dp)
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "Check Out Pesanan",
                        style = TextStyle(
                            fontSize =25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.Left
                ) {
                    Text(text = "Info Pesanan Anda :",
                        style = TextStyle(fontWeight = FontWeight.W700)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    DiantarAtauAmbil(
                        onNavigateToScreen,
                        alamatByName = alamatByName
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RingkasanPesanan(selectedKatalis)
                    Spacer(modifier = Modifier.height(10.dp))
                    Pembayaran(
                        selectedImageUri,
                        onSelectImageUri = {
                            selectedImageUri = it
                        }
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                }

            }
            Column(
                verticalArrangement = Arrangement.Bottom
            ){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = androidx.compose.ui.graphics.Color.White,
                        containerColor = Brown
                    ),
                    onClick = {
                        if (selectedImageUri?.path!!.isEmpty()) {
                            Toast.makeText(context, "Select an Image", Toast.LENGTH_SHORT).show()
                        }
                        else {
                            uploadImageToFirebaseStorage(
                                "User_${userData.userId}",
                                selectedImageUri!!,
                                onSuccess = {
                                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                                },
                                onError = {
                                    Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }

                        selectedKatalis.forEach {
                            pesananViewModel.createListIdPesanan(newDaftarKatalis = DaftarKatalis(
                                id_katalis = it.idKatalis,
                                quantity = it.quantity
                            ))
                        }

                        pesananViewModel.createPesanan(newPesanan = Pesanan(
                            id_customer =  userData.userId,
                            id_hotel =  "it.id_hotel",
                            id_kurir = "it.id_hotel",
                            list_id_daftar_katalis = "D60ExMtN67d8hiRePl9X , RgV4GzXJnNZ1DBXHy5qd",
                            total_harga = 50000f,
                            transfer_proof_image_link = selectedImageUri.toString(),
                            StatusPesanan.MENUNGGU_KONFIRMASI_ADMIN,
                            Calendar.getInstance().time.toString()
                        ))


                        selectedKatalis.forEach {
                            pesananViewModel.decrementStok(
                                minStokKatalis =  SelectedKatalis(it.idKatalis, it.quantity),
                                selectedKatalisId = it.idKatalis
                            )
                        }
                        onNavigateToHome()
                    }
                ) {
                    Text(text = "Buat Pesanan")
                }
            }
        }
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