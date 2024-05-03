package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun ButtomButton(
    onNavigateToScreen : () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ){
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(0.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = androidx.compose.ui.graphics.Color.White,
                containerColor = Brown
            ),
            onClick = {
                onNavigateToScreen()
            }
        ) {
            Text(text = "Buat Pesanan")
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ButtomButtonPreview(){
    TryUserAppTheme {
        Surface {
            ButtomButton(
                onNavigateToScreen = {}
            )
        }
    }
}