package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import android.util.Log
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
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.common.BEGIN_QUANTITY_KATALIS
import com.example.tryuserapp.data.model.KatalisModel
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.component.QuantityCounter
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.backGroundScreen

@Composable

fun DetailPesanan(
    selectedDetailKatalis: KatalisModel,
    selectedQuantityKatalisList: SelectedKatalis?,

    onAddSelectedKatalisList : () -> Unit,
    onModifySelectedKatalisList : (Int) -> Unit,
    onRemoveSelectedKatalisListById : (String) -> Unit,
){
    Log.d("Detail Pesanan", "selectedQuantityKatalisList = $selectedQuantityKatalisList")
    Log.d("Detail Pesanan", "selectedQuantityKatalisList.idKatalis = ${selectedQuantityKatalisList?.idKatalis}")
    Log.d("Detail Pesanan", "selectedQuantityKatalisList.quantity = ${selectedQuantityKatalisList?.quantity}")

    Column(modifier = Modifier
        .fillMaxSize()
        .background(backGroundScreen)
        .padding(16.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = selectedDetailKatalis.namaKatalis,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center)
        {
           Image(
               painter = painterResource(id = R.drawable.ic_launcher_background),
               contentDescription = "Gambar Makanan",
               modifier = Modifier
                   .height(100.dp)
                   .width(100.dp)
           )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(16.dp)
                )
                .background(Orange)
                .padding(16.dp)
                .fillMaxWidth(),

        ) {
            Text(
                text = "Harga Jual : ${selectedDetailKatalis.hargaJual}",
                style = TextStyle(fontSize = 20.sp,  fontWeight = FontWeight.Bold)
            )

            Text(
                text = "Porsi Jual : ${selectedDetailKatalis.porsiJual}",
                style = TextStyle(fontSize = 20.sp,  fontWeight = FontWeight.Bold)
            )

            Text(
                text = "Stok : ${selectedDetailKatalis.stok}",
                style = TextStyle(fontSize = 20.sp,  fontWeight = FontWeight.Bold)
            )

            Text(
                text = "Bahan Bahan : ",
                style = TextStyle(fontSize = 20.sp,  fontWeight = FontWeight.Bold)
            )

            Text(text = selectedDetailKatalis.komposisi.joinToString { it })
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.Right
        ){

            // TODO : change later
            QuantityCounter(
                selectedQuantityKatalis = selectedQuantityKatalisList?.quantity,
                onAddSelectedKatalisList = onAddSelectedKatalisList,
                onModifySelectedKatalisList = onModifySelectedKatalisList,
                onRemoveSelectedKatalisListById = {
                    onRemoveSelectedKatalisListById(selectedDetailKatalis.id)
                },
                katalisModel = KatalisModel()
            )
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
            DetailPesanan(
                KatalisModel(

                ),
                onAddSelectedKatalisList = {},
                onModifySelectedKatalisList = {},
                onRemoveSelectedKatalisListById = {},

                selectedQuantityKatalisList = SelectedKatalis(
                    "",
                    1,
                    stokKatalis = 0
                )
            )
        }
    }
}