package com.example.duolingoapplication.ui.shared_components.composables.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun WordFarmLayout(
    content : @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    Layout (content = content) { measurables, constraints ->
        val placables = measurables.map {
            it.measure(constraints)
        }


        val placableHeight = placables.first().height
        val placableWidth = placables.first().width
        val padding = 16.dp.roundToPx()
        val lineHeight = placableHeight + padding
//        val lineCount = ceil(placables.sumOf { it.width + padding } * 1f / constraints.maxWidth).roundToInt() + 2
        val lineCount = 3
        var contentHeight = lineCount * placableHeight + (lineCount - 1) * padding

        layout(constraints.maxWidth, contentHeight) {
            var x = 0
            var y = 0
            placables.forEach {
                it.place(x,y)
                if (x + (it.width + padding) * 2 >= constraints.maxWidth) {
                    x = 0
                    y += it.height + padding
                } else x += it.width + padding
            }
        }
    }
}