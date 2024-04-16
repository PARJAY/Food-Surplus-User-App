package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.common.DUMMY_STORAGE_LOCATION
import com.example.tryuserapp.tools.FirebaseHelper.Companion.uploadImageToFirebaseStorage
import com.example.tryuserapp.ui.component.ButtomButton
import com.example.tryuserapp.ui.component.DiantarAtauAmbil
import com.example.tryuserapp.ui.component.Pembayaran
import com.example.tryuserapp.ui.component.RingkasanPesanan
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun ScreenCheckOut(
    onNavigateToHome : () -> Unit,
){

    val context = LocalContext.current

    val scrollState = rememberScrollState()

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

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
            .verticalScroll(state = scrollState)
        ) {
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Check Out Pesanan",
                    style = TextStyle(
                        fontSize =25.sp,
                        fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
                ) {
                Text(text = "Info Pesanan Anda :",
                    style = TextStyle(fontWeight = FontWeight.W700)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Column {
    //            InfoPesanan("Capcay",
    //                "Hotel Megah",
    //                10.000f,
    //                "100 gram")
                Spacer(modifier = Modifier.height(10.dp))
                DiantarAtauAmbil()
                Spacer(modifier = Modifier.height(10.dp))
                RingkasanPesanan()
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
            modifier = Modifier.fillMaxSize(),
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
                    if (selectedImageUri?.path!!.isNotEmpty()) {
                        uploadImageToFirebaseStorage(
                            DUMMY_STORAGE_LOCATION,
                            selectedImageUri!!,
                            onSuccess = {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            },
                            onError = {
                                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
                            }
                        )
                    } else {
                        Toast.makeText(context, "Select an Image", Toast.LENGTH_SHORT).show()
                    }
                    onNavigateToHome()
                }
            ) {
                Text(text = "Buat Pesanan")
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenCheckOutPreview() {
    TryUserAppTheme {
        Surface {
            ScreenCheckOut(
                onNavigateToHome = {}
            )
        }
    }
}
