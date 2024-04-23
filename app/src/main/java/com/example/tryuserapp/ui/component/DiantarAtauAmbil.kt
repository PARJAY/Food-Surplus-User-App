package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.TryUserAppTheme


data class Toggleableinfo(
    val isChecked: Boolean,
    val text: String,
    val textview : Boolean,
    val extretextview : Boolean,
)
@Composable
fun DiantarAtauAmbil(
    onNavigateToScreen : (String) -> Unit,
    alamatByName : String
){
    var Alamat by remember {
        mutableStateOf("")
    }
    val radioButtons = remember{
        mutableStateListOf(
            Toggleableinfo(
                isChecked = true,
                text = "Ambil Sendiri",
                textview = false,
                extretextview = false
            ),
            Toggleableinfo(
                isChecked = false,
                text = "Diantar",
                textview = true,
                extretextview = false
            ),
            Toggleableinfo(
                isChecked = false,
                text = "Donasi",
                textview = true,
                extretextview = true
            ),
        )
    }
    Box (
        modifier = Modifier
            .wrapContentSize()
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp)
    ) {
        Column {
            radioButtons.forEachIndexed{index, info ->
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            radioButtons.replaceAll {
                                it.copy(
                                    isChecked = it.text == info.text
                                )
                            }
                        }
                        .padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ){
                    Text(text = info.text)
                    RadioButton(
                        selected = info.isChecked,
                        onClick = {
                            radioButtons.replaceAll {
                                it.copy(
                                    isChecked = it.text == info.text
                                )
                            }
                        }
                    )


                }
            }
            if (radioButtons[2].isChecked == true){
//        display yayasna terdaftar menu dropdown
                DropDownYayasan()
            }
            if (!radioButtons[0].isChecked == true){

                Button(onClick = { onNavigateToScreen(Screen.MapsScreen.route) }) {
                    Text(text = "Pilih Lokasi")
                }
                Text(text = alamatByName)
//        display input text Alamat
// //               TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            onNavigateToScreen(Screen.MapsScreen.route)
//                        }
//                        .padding(16.dp),
//                    value = Alamat,
//                    onValueChange = {
//                            newValue ->
//                        Alamat = newValue
//                    },
//                    readOnly = false,
//                    label = { Text("alamat") }
//                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RadioButtonsPreview(){
    TryUserAppTheme {
        Surface {
            DiantarAtauAmbil(onNavigateToScreen = {},
                alamatByName = String()
            )
        }
    }
}


