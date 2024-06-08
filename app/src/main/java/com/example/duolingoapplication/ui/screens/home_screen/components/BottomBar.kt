package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.Blue
import com.example.duolingoapplication.ui.theme.Gray
import com.example.duolingoapplication.ui.theme.GrayDark

@Composable
@Preview
fun BottomBar() {

    Box(
        modifier = Modifier
            .zIndex(10f)
            .background(Color.White)
            .drawBehind {
                drawLine(
                    color = Gray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2.dp.toPx()
                )
            }
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp, bottom = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box (
                modifier = Modifier
                    .background(Blue.copy(0.3f), shape = RoundedCornerShape(6.dp))
                    .border(width = 1.dp, color = Blue, shape = RoundedCornerShape(6.dp))
                    .padding(10.dp)
            ) {
                BarIcon(R.drawable.img_azerbaijan)
            }
            BarIcon(R.drawable.img_fire, "1", 0f)
            BarIcon(R.drawable.img_gem, "505")
            BarIcon(R.drawable.img_heart, "5")
        }
    }
}