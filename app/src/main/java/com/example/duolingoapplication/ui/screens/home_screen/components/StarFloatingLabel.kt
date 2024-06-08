package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.Hare
import com.example.duolingoapplication.ui.theme.Polar
import com.example.duolingoapplication.ui.theme.Swan

@Composable
@Preview
fun StarFloatingLabel() {

    val textMeasurer = rememberTextMeasurer()
    Box(
        modifier = Modifier
            .size(100.dp)
            .drawWithCache {
                val textSize = textMeasurer.measure("START").size
                val padding = 12.dp.toPx()
                val rectSize = Size(padding+ textSize.width+padding, padding+ textSize.height+padding)
                val totalTranslation = 0.dp.toPx()

                val indicatorSize = Size(width = 8.dp.toPx(), 8.dp.toPx())
                onDrawWithContent {
                    drawContent()
                    translate(top = totalTranslation) {
                        //box
                        drawRoundRect(
                            color = Color.White,
                            topLeft = Offset(x = size.center.x - rectSize.width / 2f, 0f),
                            size = rectSize,
                            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx())
                        )
                        //box border
                        drawRoundRect(
                            color = Hare,
                            topLeft = Offset(x = size.center.x - rectSize.width / 2f, 0f),
                            size = rectSize,
                            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
                            style = Stroke(width = 1.5.dp.toPx())
                        )

                        drawText(
                            textMeasurer = textMeasurer,
                            text = "START",
                            style = TextStyle(
                                color = FeatherGreen,
                                fontWeight = FontWeight.Bold
                            ),
                            topLeft = Offset(
                                x = size.center.x - textSize.width / 2f,
                                y = padding
                            )
                        )

                        val indicatorPath = Path().apply {
                            moveTo(x = size.center.x - indicatorSize.width / 2f, y = rectSize.height)
                            lineTo(x = size.center.x, y = rectSize.height + indicatorSize.height)
                            lineTo(x = size.center.x + indicatorSize.width / 2f, y = rectSize.height)
                        }

                        drawPath(
                            path = indicatorPath,
                            color = Color.White,
                            style = Fill
                        )
                        val indicatorBorderPath = Path().apply {
                            moveTo(x = size.center.x - indicatorSize.width / 2f, y = rectSize.height)
                            lineTo(x = size.center.x, y = rectSize.height + indicatorSize.height)
                            lineTo(x = size.center.x + indicatorSize.width / 2f, y = rectSize.height)
                        }
                        drawPath(
                            path = indicatorBorderPath,
                            color = Hare,
                            style = Stroke(width = 1.dp.toPx())
                        )
                    }
                }
            }
    )
}