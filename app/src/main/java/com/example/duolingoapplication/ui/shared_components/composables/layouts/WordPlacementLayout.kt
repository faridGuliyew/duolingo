package com.example.duolingoapplication.ui.shared_components.composables.layouts

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.ceil
import kotlin.math.roundToInt

@Composable
fun WordPlacementLayout(
    modifier: Modifier = Modifier,
    content : @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    val screenWidthDp = config.screenWidthDp
    Layout (
        modifier = modifier.drawBehind {
            val placableHeight = 20.dp.roundToPx()
            val linePadding = 12.dp.roundToPx()
            repeat(2 /*(size.height / 20.dp.roundToPx()).roundToInt()*/) {
                val contentHeight = (it + 1) * placableHeight + it * linePadding
                drawLine(
                    color = Color.Black,
                    start = Offset(0f,  contentHeight + linePadding / 2f),
                    end = Offset(size.width, contentHeight + linePadding / 2f),
                    strokeWidth = 1.dp.toPx()
                )
            }
        },
        content = content
    ) { measurables, constraints ->
        val placables = measurables.map {
            it.measure(constraints)
        }


        val placableHeight = placables.first().height
        val placableWidth = placables.first().width
        val padding = 12.dp.roundToPx()
        val lineHeight = placableHeight + padding
//        val lineCount = ceil(placables.sumOf { it.width + padding } * 1f / constraints.maxWidth).roundToInt() + 2
        val lineCount = 3
        var contentHeight = lineCount * placableHeight + (lineCount - 1) * padding

        layout(constraints.maxWidth, contentHeight) {
            var x = 0
            var y = 0
//            placables.forEach {
//                it.place(x,y)
//                if (x + (it.width + padding) * 2 >= constraints.maxWidth) {
//                    x = 0
//                    y += it.height + padding
//                } else x += it.width + padding
//            }
        }
    }
}

@Preview (showBackground = true)
@Composable
private fun WordPlacementLayoutPrev() {
    val fontSize = with(LocalDensity.current) {20.dp.toSp()}
    WordPlacementLayout (modifier = Modifier) {
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")
        Text(modifier = Modifier.height(20.dp), text = "Thank you")

    }
}