package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.Blue
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.FeatherGreenDark
import com.example.duolingoapplication.ui.theme.Hare
import com.example.duolingoapplication.ui.theme.Swan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SelectableStarButton(
    colorMain: Color = FeatherGreen,
    colorDark : Color = FeatherGreenDark,
    onStarClicked: (coordinateInRoot: Float, isInteractive : Boolean) -> Unit,
    isInitial: Boolean = false,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    var isClicked by remember {
        mutableStateOf(false)
    }
    var positionInRoot by remember {
        mutableFloatStateOf(0f)
    }
    val animatedTranslation by animateFloatAsState(
        targetValue = if (isClicked) 0f else -20f
    )
    val textMeasurer = rememberTextMeasurer()
    val labelTranslation by rememberInfiniteTransition().animateFloat(
        initialValue = -40f,
        targetValue = -60f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "label"
    )
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .onGloballyPositioned {
                positionInRoot = it.positionInRoot().y
            }
            .drawWithCache {
                val text = if (isInitial) "START" else "JUMP HERE?"
                val textStyle = TextStyle(
                    color = colorMain,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                val textSize = textMeasurer.measure(
                    text = text,
                    style = textStyle
                ).size
                val padding = 18.dp.toPx()
                val rectSize =
                    Size(padding + textSize.width + padding, padding + textSize.height + padding)
                val totalTranslation = labelTranslation.dp.toPx()

                val indicatorSize = Size(width = 8.dp.toPx(), 8.dp.toPx())

                val textPaint = Paint()
                    .asFrameworkPaint()
                    .apply {
                        isAntiAlias = true
                        this.isFakeBoldText = true
                        this.color = colorMain.toArgb()
                        this.textSize = 18.sp.toPx()
                    }
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
                            color = Swan,
                            topLeft = Offset(x = size.center.x - rectSize.width / 2f, 0f),
                            size = rectSize,
                            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
                            style = Stroke(width = 1.5.dp.toPx())
                        )

                        //text
                        drawContext.canvas.nativeCanvas.apply {
                            this.drawText(
                                text,
                                size.center.x - textSize.width / 2f,
                                padding * 2f,
                                textPaint
                            )
                        }
                        //little rectangle at the bottom
                        val indicatorPath = Path().apply {
                            moveTo(
                                x = size.center.x - indicatorSize.width / 2f,
                                y = rectSize.height
                            )
                            lineTo(x = size.center.x, y = rectSize.height + indicatorSize.height)
                            lineTo(
                                x = size.center.x + indicatorSize.width / 2f,
                                y = rectSize.height
                            )
                        }
                        drawPath(
                            path = indicatorPath,
                            color = Color.White,
                            style = Fill
                        )
                        //indicator border
                        drawPath(
                            path = indicatorPath,
                            color = Swan,
                            style = Stroke(width = 1.dp.toPx())
                        )
                    }
                }
            }
            .border(color = Swan, width = 6.dp, shape = CircleShape)
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0.5f)
                rotationX = 30f
            }
            .padding(12.dp)
            .background(color = colorDark, shape = CircleShape)

    ) {
        Image(
            modifier = Modifier
                .width(72.dp)
                .graphicsLayer {
                    translationY = animatedTranslation
                }
                .padding(0.dp)
                .background(color = colorMain, shape = CircleShape)
                .padding(20.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        scope.launch {
                            isClicked = true
                            delay(80)
                            onStarClicked(positionInRoot, true)
                            isClicked = false
                        }
                    }
                }
                .pointerInput(Unit) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            isClicked = true
                        }, onDragEnd = {
                            isClicked = false
                            onStarClicked(positionInRoot, true)
                        }
                    ) { _, _ -> }
                },
            painter = painterResource(id = if (isInitial) R.drawable.ic_star else R.drawable.ic_skip),
            colorFilter = ColorFilter.tint(color = Color.White),
            contentDescription = "star"
        )
    }
}