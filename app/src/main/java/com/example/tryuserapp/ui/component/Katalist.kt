package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.tools.FirebaseHelper.Companion.getFileFromFirebaseStorage
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun Katalis(
    katalisModel: KatalisModel,
    onNavigateToScreen: (String) -> Unit,

    selectedQuantityKatalis: Int? = 0,
    onAddSelectedKatalisList : () -> Unit,
    onModifySelectedKatalisList : (Int) -> Unit,
    onRemoveSelectedKatalisListById : () -> Unit,
) {
    var imageURI by remember { mutableStateOf<Uri>(Uri.EMPTY) }

    Log.d("Katalis Component", "Hotel_${katalisModel.idHotel}/${katalisModel.imageLink}")
    getFileFromFirebaseStorage(
        fileReference = "Hotel_${katalisModel.idHotel}/${katalisModel.imageLink}",
        onSuccess = { imageURI = it},
        onError = {}
    )

    Button(
        modifier = Modifier
            .wrapContentHeight()
            .width(380.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = HijauMuda
        ),
        onClick = { onNavigateToScreen(Screen.ScreenDetailKatalis.route) },
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage (
                model = imageURI,
                contentDescription = "Katalis picture",
                modifier = Modifier
                    .height(65.dp)
                    .width(65.dp),
//                .padding(top = 10.dp, bottom = 5.dp, start = 10.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(end = 0.dp)) {
                Text(
                    text = katalisModel.namaKatalis,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable { Log.d("stok", "$katalisModel.stok") }
                )
                Text(
                    text = "${katalisModel.hargaJual}/${katalisModel.porsiJual}",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Text(
                    text = "Stok : ${katalisModel.stok}",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Right
            ) {
                // TODO : (add logic) quantity counter cant be surpass the stok katalis
                QuantityCounter(
                    selectedQuantityKatalis,
                    onAddSelectedKatalisList,
                    onModifySelectedKatalisList,
                    onRemoveSelectedKatalisListById,
                    katalisModel = katalisModel
                )
            }
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
                katalisModel = KatalisModel(
                    namaKatalis = "Capcay",
                    hargaJual = 10000f,
                    porsiJual = "100 gram"
                ),
                onNavigateToScreen = {},
                onAddSelectedKatalisList = {},
                onModifySelectedKatalisList = {},
                onRemoveSelectedKatalisListById = {},
                selectedQuantityKatalis = null
            )
        }
    }
}