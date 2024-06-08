package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.screens.home_screen.models.UnitData
import com.example.duolingoapplication.ui.shared_components.composables.text.TitleText
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
@Preview
fun TopBar(units: List<UnitData> = listOf(UnitData()), visibleUnitIndex: Int = 0) {
    val systemUiController = rememberSystemUiController()
    val animatedColor by animateColorAsState(
        targetValue = units[visibleUnitIndex].color,
        animationSpec = tween(durationMillis = 600)
    )
    systemUiController.setStatusBarColor(animatedColor)
    systemUiController.setNavigationBarColor(Color.White)

    Box(
        modifier = Modifier
            .zIndex(10f)
            .drawBehind {
                drawRect(
                    color = animatedColor
                )
                drawRect(
                    color = units[visibleUnitIndex].darkerColor,
                    topLeft = Offset(x = 0f, y = size.height),
                    size = Size(width = size.width, height = 2.dp.toPx())
                )
            }
            .padding(horizontal = 12.dp)
            //.padding(top = 64.dp, bottom = 6.dp)
    ) {
        Column {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 16.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BarIcon(R.drawable.img_azerbaijan)
                BarIcon(R.drawable.img_fire, "1", 0f)
                BarIcon(R.drawable.img_gem, "505")
                BarIcon(R.drawable.img_heart, "5")
            }

            Box {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TitleText(text = "Section 1: Rookie", color = Color.White, fontSize = 18.sp)
                }

                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    tint = Color.White,
                    contentDescription = "up"
                )
            }
        }
    }


}

@Composable
fun BarIcon(@DrawableRes icon : Int, text : String? = null, saturation : Float = 1f) {
    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(saturation) })
        )
        text?.let {
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = it, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}