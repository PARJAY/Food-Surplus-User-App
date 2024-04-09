package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.tryuserapp.R
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun TidakAdaPesanan(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column {

            Image(painter = painterResource(id = R.drawable.undraw_empty_cart_co35), contentDescription = "Tidak ada Pesanan")
            Text(text = "Tidak ada data pesanan anda")

        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TidakAdaPesananPreview(){
    TryUserAppTheme {
        Surface {
            TidakAdaPesanan()
        }
    }
}