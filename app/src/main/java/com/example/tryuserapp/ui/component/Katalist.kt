package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun Katalis(
            urlGambar : Int,
            nameMakanan : String,
            hotel : String,
            harga : Float,
            jumlah : String,
            navController: NavController
) {
    var jumlah by remember {
        mutableIntStateOf(0)
    }

    Row(
        modifier = Modifier
            .height(80.dp)
            .width(380.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { navController.navigate(Screen.ScreenDetailPesanan.route) }
    ) {
       Image(painter = painterResource(id = urlGambar),
           contentDescription = "Gambar Makanan",
           modifier = Modifier
               .height(70.dp)
               .width(63.dp)
               .padding(top = 10.dp, bottom = 5.dp, start = 10.dp),
       )
        Column(
            modifier = Modifier.padding(end = 0.dp)
        ) {
            Text(
                text = nameMakanan,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
            ),
            modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text = hotel,
                style = TextStyle(
                    fontSize = 16.sp
            ),
            modifier = Modifier.padding( start = 16.dp)
            )
            Text(
                text = "$harga/$jumlah",
                style = TextStyle(
                    fontSize = 16.sp
            ),
            modifier = Modifier.padding( start = 16.dp)
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right
        ){
                TambahKurang()
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KatalisPreview(){
    TryUserAppTheme {
        Surface {
            Katalis(
                R.drawable.ic_launcher_background,
                "Capcay",
                "Hotel Megah",
                10.000f,
                "100 Gram",
                navController = rememberNavController()
            )
        }
    }
}


