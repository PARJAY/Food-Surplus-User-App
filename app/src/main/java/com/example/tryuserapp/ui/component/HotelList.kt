package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.R
import com.example.tryuserapp.data.model.HotelModel
import com.example.tryuserapp.logic.StatusHotel
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenEvent
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun HotelList(
    hotelModel: HotelModel,
    onNavigateToScreen: (String) -> Unit,
) {
    Row (
        modifier = Modifier
            .height(80.dp)
            .width(380.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(bottom = 8.dp)
            .clickable {
                onNavigateToScreen(Screen.KatalisScreen.route)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Left
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Gambar Makanan",
            modifier = Modifier
                .height(70.dp)
                .width(63.dp)
                .padding(top = 10.dp, bottom = 5.dp, start = 10.dp),
        )
        Column(modifier = Modifier.padding(end = 0.dp)) {
            Text(
                text = hotelModel.name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            )
            Text(
                text = hotelModel.alamat,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun HotelListPreview(){
    TryUserAppTheme {
        Surface {
            HotelList(
                hotelModel = HotelModel(
                    idHotel = "dasd",
                    name = "Hotel Gilang",
                    phoneNumber = "1231132",
                    email = "hotelgilang@gmail.com",
                    listIdKatalis = listOf(""),
                    statusHotel = "SUDAH_DI_ACC"
                ),
              onNavigateToScreen = {}
            )
        }
    }
}