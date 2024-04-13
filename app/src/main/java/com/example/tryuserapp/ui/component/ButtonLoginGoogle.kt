package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.tryuserapp.R
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun GoogleButton(){
    Button(
        modifier = Modifier
            .width(100.dp)
            .height(70.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = { /*TODO*/ }
    ) {
        Image(painter = painterResource(id = R.drawable.devicon_google), contentDescription ="Google Icon" )
        Spacer(modifier = Modifier.width(30.dp))
        Text(text = "Sign in Wiht Google")
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun GoogleButtonPreview(){
    TryUserAppTheme {
        Surface {
            GoogleButton()
        }
    }
}