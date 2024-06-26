package com.example.tryuserapp.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tryuserapp.R
import com.example.tryuserapp.presentation.sign_in.SignInState
import com.example.tryuserapp.ui.theme.Brown
import com.example.tryuserapp.ui.theme.HijauMuda
import com.example.tryuserapp.ui.theme.TryUserAppTheme
import com.example.tryuserapp.ui.theme.Krem

@Composable
fun ScreenLogin(
    state: SignInState,
    onSignInClick : () -> Unit,
) {
    
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInErrorMessage) {
        state.signInErrorMessage?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HijauMuda),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier
                .height(525.dp)
                .width(300.dp)
                .background(HijauMuda),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Krem,
                contentColor = Color.Black
            ),
            onClick = { /*TODO*/ }
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(45.dp))
                Text(text = "Login",
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Selamat datang, silahkan Login dengan akun Google anda untuk melanjutkan ke halaman berikutnya",
                    textAlign = TextAlign.Center
                )
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Button(
                        modifier = Modifier
                            .width(230.dp)
                            .height(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ),
                        onClick = { onSignInClick() }
                    ) {
                        Image(painter = painterResource(id = R.drawable.devicon_google), contentDescription ="Google Icon" )
                        Spacer(modifier = Modifier.width(25.dp))
                        Text(text = "Sign in With Google")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenLoginPreview() {
    TryUserAppTheme {
        Surface {
            ScreenLogin(
                state = SignInState(false, null),
                onSignInClick = {}
            )
        }
    }
}