package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun InfoPesanan(){
    Column(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .height(80.dp)
                .width(380.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .width(63.dp)
                    .padding(top = 10.dp, bottom = 5.dp, start = 10.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                onClick = { /*TODO*/ }
            ) {

            }
            Column(
                modifier = Modifier.padding(end = 65.dp)
            ) {
                Text(
                    text = "Capcay",
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
                    text = "Rp.10.000 / 100 gram",
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding( start = 16.dp)
                )
            }
            TambahKurang()

        }
        Row(
            modifier = Modifier
                .height(80.dp)
                .width(380.dp)
                ,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .width(63.dp)
                    .padding(top = 10.dp, bottom = 5.dp, start = 10.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                onClick = { /*TODO*/ }
            ) {

            }
            Column(
                modifier = Modifier.padding(end = 65.dp)
            ) {
                Text(
                    text = "Capcay",
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
                    text = "Rp.10.000 / 100 gram",
                    style = TextStyle(
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding( start = 16.dp)
                )
            }
            TambahKurang()

        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun InfoPesananPreview(){
    TryUserAppTheme {
        Surface {
            InfoPesanan()
        }
    }
}