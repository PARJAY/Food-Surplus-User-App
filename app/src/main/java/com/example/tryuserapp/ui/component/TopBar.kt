package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun TopBar(
    userData: UserData?,
    onNavigateToScreen : (String) -> Unit
){
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
            if (userData?.profilePictureUrl != null){
                AsyncImage(
                    model = userData.profilePictureUrl ,
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
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
            TopBar(
                userData = UserData(),
                onNavigateToScreen = {}
            )
        }
    }
}