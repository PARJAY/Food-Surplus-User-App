package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun Pembayaran(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Pembayaran",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

        )
        Text(text = "No Rekening Admin : 123123123")
        Text(text = "Input Foto Bukti Pembayaran :")
        Spacer(modifier = Modifier.size(width = 0.dp, height = 5.dp))
        Button(
            modifier = Modifier
                .width(120.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Input")
        }
        Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
        Row {
            Text(text = "Preview :")
            Spacer(modifier = Modifier.size(width = 40.dp, height = 0.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(text = "Foto")
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PembayaranPreview(){
    TryUserAppTheme {
        Surface {
            Pembayaran()
        }
    }
}