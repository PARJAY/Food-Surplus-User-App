package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun TopBar(navController : NavController){
    Box (modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Brown)
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
            Text(
                text = "Nama",
                style = TextStyle(
                    color = Color.White
                )
            )
            Button(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp),
                shape = RoundedCornerShape(16.dp) ,
                onClick = {  navController.navigate("screenProfile") }
            ) {

            }
        }

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TopBarPreview(){
    TryUserAppTheme {
        Surface {
            TopBar(navController = rememberNavController())
        }
    }
}