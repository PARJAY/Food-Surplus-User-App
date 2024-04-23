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
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.katalis_screen.KatalisScreenEvent
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun Katalis(
    katalisModel: KatalisModel,
    onNavigateToScreen: (String) -> Unit,
    onKatalisScreenEvent: (KatalisScreenEvent) -> Unit,

    selectedQuantityKatalis: Int? = 0,
    onAddSelectedKatalisList : () -> Unit,
    onModifySelectedKatalisList : (Int) -> Unit,
    onRemoveSelectedKatalisListById : () -> Unit,

    onModifyQuantity: (katalisId : String, OrderAction) -> Unit,
) {

    Row (
        modifier = Modifier
            .height(80.dp)
            .width(380.dp)
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onNavigateToScreen(Screen.ScreenDetailPesanan.route)
            },
        horizontalArrangement = Arrangement.Absolute.Right
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
                text = katalisModel.namaKatalis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 5.dp)
            )
            Text(
                text = "${katalisModel.hargaJual}/${katalisModel.porsiJual}",
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            QuantityCounter(
                selectedQuantityKatalis,
                onAddSelectedKatalisList,
                onModifySelectedKatalisList,
                onRemoveSelectedKatalisListById,
                onQuantityModified = { orderAction ->
                    onModifyQuantity(katalisModel.id, orderAction)
                }
            )
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
                onKatalisScreenEvent = {},
                onModifyQuantity = { katalisId, orderAction ->
                },
                onAddSelectedKatalisList = {},
                onModifySelectedKatalisList = {},
                onRemoveSelectedKatalisListById = {},
                selectedQuantityKatalis = null
            )
        }
    }
}


