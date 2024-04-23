package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.presentation.katalis_screen.SelectedKatalis
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun RingkasanPesanan(
    selectedKatalis: SnapshotStateList<SelectedKatalis>
) {

    var totalHarga = 0F

    selectedKatalis.forEach {
        totalHarga += (it.hargaKatalis * it.quantity)
    }



    LazyColumn(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        item {
            Column() {
                Text(
                    text = "Ringkasan Pesanan",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                )
                Spacer(modifier = Modifier.size(width = 0.dp, height = 8.dp))

            }
        }


        items(selectedKatalis) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = it.namaKatalis + " x " +  it.quantity )
                    Text(text = "Rp." + (it.hargaKatalis* it.quantity ))
                }

            }

        }
        item {
            Text(text = "Total Harga $totalHarga")

        }

    }
}


//@Preview(showBackground = true)
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
//@Composable
//fun RingkasanPesananPreview(){
//    TryUserAppTheme {
//        Surface {
//            RingkasanPesanan(selectedKatalis = SnapshotStateList<SelectedKatalis>)
//        }
//    }
//}