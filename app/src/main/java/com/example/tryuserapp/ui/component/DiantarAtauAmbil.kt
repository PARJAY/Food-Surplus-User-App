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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.HijauTua
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.White


data class Toggleableinfo(
    val isChecked: Boolean,
    val text: String,
    val textview : Boolean,
    val extretextview : Boolean,
)
@Composable
fun DiantarAtauAmbil(
    onNavigateToScreen : (String) -> Unit,
    alamatByName : String,
    radioButtons : SnapshotStateList<Toggleableinfo>,
) {
    var alamatYayasan by remember {
        mutableStateOf("")
    }

    Button(
        modifier = Modifier
            .wrapContentSize(),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = White
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column {
        radioButtons.forEachIndexed{index, info ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        radioButtons.replaceAll {
                            it.copy(isChecked = it.text == info.text)
                        }
                    }
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Text(text = info.text)
                RadioButton(
                    selected = info.isChecked,
                    onClick = {
                        radioButtons.replaceAll {
                            it.copy(isChecked = it.text == info.text)
                        }
                    }
                )
            }
        }
        if (radioButtons[2].isChecked) {
            DropDownYayasan(
                setLokasiYayasan = {
                    alamatYayasan = it
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            Column {
                Spacer(modifier = Modifier.height(7.dp))
                Text(text = "Alamat Yayasan : ")
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = if (alamatYayasan != "") alamatYayasan else "")
                Spacer(modifier = Modifier.height(4.dp))
            }
        }

        if (!radioButtons[0].isChecked) {

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = HijauTua,
                    contentColor = White
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = { onNavigateToScreen(Screen.MapsScreen.route) }) {
                Text(text = "Pilih Lokasi")
            }
            Spacer(modifier = Modifier.height(7.dp))
            Text(text = "Alamat Customer : ")
            Spacer(modifier = Modifier.height(4.dp))
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

            DiantarAtauAmbil(
                onNavigateToScreen = {},
                alamatByName = String(),
                radioButtons = radioButtons
            )
        }
    }
}


