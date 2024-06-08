package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.duolingoapplication.ui.screens.home_screen.orderToPercentage

@Composable
fun UnitContent(
    unitIndex: Int,
    colorMain : Color,
    colorDark : Color,
    @DrawableRes unitImage : Int,
    starCount: Int,
    onStarClicked : (coordinateInRoot : Float, isInteractive : Boolean) -> Unit
) {
    Box {
        Column (
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            repeat(starCount) { starIndex ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val alignPercentage = remember {
                        orderToPercentage(starIndex, unitIndex % 2 == 0)
                    }
                    Spacer(modifier = Modifier.fillMaxWidth(alignPercentage))
                    if (starIndex == 0) SelectableStarButton(
                        isInitial = unitIndex == 0,
                        colorMain = colorMain,
                        colorDark = colorDark,
                        onStarClicked = onStarClicked
                    )
                    else StarButton(onStarClicked)
                }
            }
        }

        Image(
            modifier = Modifier
                .size(200.dp)
                .align(alignment = if (unitIndex % 2 == 0) Alignment.CenterEnd else Alignment.CenterStart),
            painter =  rememberAsyncImagePainter(unitImage),
            colorFilter = ColorFilter.colorMatrix(
                colorMatrix = ColorMatrix().apply {
                    setToSaturation(0f)
                }
            ),
            contentDescription = "duo"
        )
    }
}
