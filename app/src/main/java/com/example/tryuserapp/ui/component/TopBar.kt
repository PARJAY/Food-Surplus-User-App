package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tryuserapp.presentation.sign_in.UserData
import com.example.tryuserapp.ui.navigation.Screen
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.HijauTua
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun TopBar(userData: UserData?, onNavigateToScreen: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(HijauTua)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        if (userData?.username != null) {
            Text(
                text = userData.username,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = Color.White
            )

            AsyncImage(
                model = userData.profilePictureUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable { onNavigateToScreen(Screen.ScreenProfile.route) },
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TopBarPreview(){
    TryUserAppTheme {
        Surface {
            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter)
            {
                TopBar(
                    userData = UserData(
                        username = "Parjay",
                        profilePictureUrl = "https://lh3.googleusercontent.com/a/ACg8ocJc8tgd2tjP0JUZf105b-u4rQmyY35UHgqcyb6q7gNplUz4nDx7=s96-c"
                    ),
                    onNavigateToScreen = {}
                )
            }
        }
    }
}