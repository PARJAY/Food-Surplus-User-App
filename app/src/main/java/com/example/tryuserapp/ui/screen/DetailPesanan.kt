package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import android.util.Size
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.ui.component.TambahKurang
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable
fun DetailPesanan(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backGroundScreen),){
        Column(
            modifier = Modifier
                .padding(16.dp)

        ){
            Image(
                modifier = Modifier
                    .size(20.dp)
                    .clickable { navController.navigate("HomeScreen") },
                painter = painterResource(id = R.drawable.back)
                , contentDescription = "Back")
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(text = "Capcay",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Button(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    onClick = { /*TODO*/ }
                ) {

                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(Orange)
                    .padding(16.dp),

            ) {
                Text(text = "Bahan Bahan : ",
                    style = TextStyle(fontSize = 20.sp,  fontWeight = FontWeight.Bold)
                )
                Text(text =
                        "1. Sayuran segar seperti wortel, kembang kol, brokoli, kacang polong, jamur, sawi hijau, dan buncis.\n" +
                        "2. Daging ayam, sapi, atau udang, yang biasanya dipotong kecil-kecil.\n" +
                        "3. Bawang putih dan bawang merah, yang diiris tipis atau dicincang.\n" +
                        "4. Saos tiram, kecap manis, kecap asin, dan saos cabai, untuk memberikan rasa dan aroma khas.\n" +
                        "5. Minyak sayur untuk menumis bahan-bahan tersebut.\n" +
                        "6. Garam, lada, dan gula, untuk menyesuaikan rasa sesuai selera.\n" +
                        "7. Tepung maizena atau tepung terigu, untuk mengentalkan saus jika diperlukan.")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row (modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Right
                ){
                TambahKurang()
            }
            }
        }
    }


//Button(onClick = {navController.navigate("HomeScreen") }) {
    //                Text(text = "Back")
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailPesananPreview() {
    TryUserAppTheme {
        Surface {
            DetailPesanan(navController = rememberNavController())
        }
    }
}