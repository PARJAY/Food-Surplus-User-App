package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.graphics.drawable.shapes.Shape
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.HijauTua
import com.example.tryuserapp.ui.theme.Orange
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.White

@Composable
fun Pembayaran(
    selectedImageUri : Uri?,
    onSelectImageUri : (Uri) -> Unit
){

    val singelPhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {uri -> uri?.let { onSelectImageUri(it) } }
    )

    Button(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            containerColor = HijauMuda,
            contentColor = White
        ),
        shape = RoundedCornerShape(16.dp)
    ){
        Column(modifier = Modifier.padding(top = 10.dp)){
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
                colors = ButtonDefaults.buttonColors(
                    containerColor = HijauTua,
                    contentColor = Color.White
                ),
                onClick = {
                    singelPhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                }
            ) {
                Text(text = "Input")
            }
            Spacer(modifier = Modifier.size(width = 0.dp, height = 20.dp))
            Row {
                Text(text = "Preview :")
                Spacer(modifier = Modifier.size(width = 40.dp, height = 0.dp))
                LazyColumn(
                    modifier = Modifier
                        .height(180.dp)
                ){
                    item {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
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
            Pembayaran(
                Uri.EMPTY,
                onSelectImageUri = {}
            )
        }
    }
}