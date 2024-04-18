package com.example.tryuserapp.ui.component

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tryuserapp.R
import com.example.tryuserapp.logic.OrderAction
import com.example.tryuserapp.presentation.home_screen.HomeScreenEvent
import com.example.tryuserapp.ui.theme.TryUserAppTheme

@Composable
fun QuantityCounter(
    onQuantityModified: (OrderAction) -> Unit
){
    var itemCounter by remember { mutableIntStateOf(0) }

    val operatorIconModifier = Modifier
        .border(
            BorderStroke(1.dp, Color.Black),
            shape = RoundedCornerShape(0.dp)
        )
        .height(20.dp)
        .width(20.dp)
        .padding(start = 5.dp, end = 5.dp)

    Row(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(0.dp))
            .height(20.dp)
            .background(Color.Gray),
        verticalAlignment = CenterVertically,

    ) {

        Icon(
            painterResource(R.drawable.vector__2_),
            contentDescription = "",
            modifier = operatorIconModifier.clickable {
                Log.d("Component", "Quality Counter minus icon Clicked")
                itemCounter--
                onQuantityModified(OrderAction.DECREMENT)
            }
        )

        Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))
        Text(
            modifier = Modifier.wrapContentHeight(align = CenterVertically),
            text = itemCounter.toString()
        )
        Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))

        Icon(
            Icons.Default.Add,
            contentDescription = "",
            modifier = operatorIconModifier.clickable {
                Log.d("Component", "Quality Counter plus icon Clicked")
                itemCounter++
                onQuantityModified(OrderAction.INCREMENT)
            }
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TambahKurangPreview(){
    TryUserAppTheme {
        Surface {
            QuantityCounter(
                onQuantityModified = { orderAction ->

                }
            )
        }
    }
}