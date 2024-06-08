package com.example.duolingoapplication.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.shared_components.composables.bubble.WordBubble
import com.example.duolingoapplication.ui.shared_components.composables.misc.NewWordBubble
import com.example.duolingoapplication.ui.shared_components.composables.text.PrimaryText
import com.example.duolingoapplication.ui.shared_components.composables.text.TitleText
import com.example.duolingoapplication.ui.shared_components.composables.top_app_bar.QuizTopAppBar
import com.example.duolingoapplication.ui.theme.Beetle
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.Hare
import com.example.duolingoapplication.ui.theme.MaskGreen

@Composable
@Preview(showBackground = true)
fun QuizScreen() {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    var progress by remember {
        mutableFloatStateOf(0f)
    }
    val canContinue by remember {
        derivedStateOf {
            progress > 0f
        }
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(vertical = 4.dp)
    ) {
        Scaffold(
            topBar = {
                QuizTopAppBar(progress = progress)
            }
        ) {
            var selectedWords = remember {
                mutableStateListOf<WordBubbleState>()
            }
            var availableMaxWidth by remember {
                mutableStateOf(0.dp)
            }
            val textMeasurer = rememberTextMeasurer()
            val words = remember {
                mutableStateListOf(
                    WordBubbleState("10 hours"),
                    WordBubbleState("it"),
                    WordBubbleState("almost"),
                    WordBubbleState("me"),
                    WordBubbleState("took"),
                    WordBubbleState("literally"),
                )
            }

            if (availableMaxWidth != 0.dp)
                LaunchedEffect(key1 = Unit) {
                    var sumX = 0.dp
                    var sumY = 200.dp

                    words.drop(1).forEachIndexed { index, state ->
                        val width = words[index].getWidth(density, textMeasurer)
                        if (sumX + width > availableMaxWidth) {
                            sumX = 0.dp
                            sumY += state.getHeight(density, textMeasurer)
                        } else sumX += width
                        val offset = Pair(sumX, sumY)
                        state.targetXOffset = offset.first
                        state.targetYOffset = offset.second
                        state.originalOffset = offset
                    }
                }

            LaunchedEffect(key1 = selectedWords.size) {
                progress = selectedWords.size.toFloat() / words.size.toFloat()
                repositionSelectedWords(selectedWords, availableMaxWidth, density, textMeasurer)
            }
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                //New word row
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NewWordBubble()
                    Spacer(modifier = Modifier.width(8.dp))
                    PrimaryText(
                        text = R.string.info_new_word,
                        color = Beetle,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TitleText(
                    text = R.string.title_translate
                )

                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_duo),
                    contentDescription = "duo"
                )

                Spacer(modifier = Modifier.height(32.dp))

                var bubbleHeight by remember {
                    mutableIntStateOf(0)
                }
                WordBubble(
                    modifier = Modifier
                        .alpha(0f)
                        .onSizeChanged { bubbleHeight = it.height }, text = ""
                ) {

                }

                if (bubbleHeight != 0)
                    BoxWithConstraints(modifier = Modifier
                        .fillMaxWidth()
                        .height((8 * 4).dp + bubbleHeight.dp * 2)
                        .drawBehind {
                            repeat(3) {
                                drawLine(
                                    color = Color.Black,
                                    start = Offset(0f, it * (bubbleHeight + 16.dp.toPx())),
                                    end = Offset(size.width, it * (bubbleHeight + 16.dp.toPx()))
                                )
                            }
                        }
                    ) {
                        availableMaxWidth = this.maxHeight
                        words.forEachIndexed { index, item ->
                            val animatedOffsetX by animateDpAsState(targetValue = item.targetXOffset)
                            val animatedOffsetY by animateDpAsState(targetValue = item.targetYOffset)

                            WordBubble(
                                modifier = Modifier
                                    .offset {
                                        IntOffset(
                                            x = animatedOffsetX.roundToPx(),
                                            y = animatedOffsetY.roundToPx()
                                        )
                                    }
                                    .background(Hare, RoundedCornerShape(8.dp)),
                                text = item.text,
                                onClick = {
                                    if (item.isSelected) selectedWords.remove(item)
                                    else selectedWords.add(item)
                                    item.isSelected = item.isSelected.not()
                                }
                            )
                        }
                    }
                //end of main screen
            }
        }
        //end of whole screen
    }
    val animatedScale by animateFloatAsState(
        targetValue = if (showDialog) 1f else 0f,
        animationSpec = tween(100, easing = LinearEasing)
    )
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.BottomCenter)
        .drawBehind {
            scale(scaleY = animatedScale, scaleX = 1f) {
                drawRect(
                    color = MaskGreen,
                    size = Size(size.width, size.height),
                    topLeft = Offset(y = 0f, x = 0f)
                )
            }
        }
        .padding(horizontal = 16.dp)
        .padding(bottom = 48.dp), contentAlignment = Alignment.BottomCenter) {
        Column {
            Spacer(modifier = Modifier.height(32.dp))
            if (showDialog)
                TitleText(
                    text = "No time for validation or animation, probably wrong answer",
                    color = Color.White
                )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = if (canContinue) FeatherGreen else Hare),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    showDialog = true
                }
            ) {
                PrimaryText(text = "CHECK", color = Color.White)
            }
        }
    }
}

class WordBubbleState(
    val text: String,
) {
    var targetXOffset by mutableStateOf(0.dp)
    var targetYOffset by mutableStateOf(200.dp)

    var isSelected = false
        set(value) {
            if (value == false) resetToOriginalPosition()
            field = value
        }

    var originalOffset = Pair(0.dp, 200.dp)

    private fun resetToOriginalPosition() {
        targetXOffset = originalOffset.first
        targetYOffset = originalOffset.second
    }

    fun getWidth(density: Density, textMeasurer: TextMeasurer): Dp {
        return 32.dp + textMeasurer.measure(
            text = text,
            style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        ).size.width.toDp(density) + 12.dp //padding
    }

    fun getHeight(density: Density, textMeasurer: TextMeasurer): Dp {
        return 20.dp + textMeasurer.measure(
            text = text,
            style = TextStyle(
                fontSize = 20.sp, fontWeight = FontWeight.Bold
            )
        ).size.height.toDp(density) + 10.dp //padding
    }
}


fun Int.toDp(density: Density): Dp {
    return with(density) { this@toDp.toDp() }
}


//Called each time there is a change in the selected words
fun repositionSelectedWords(
    states: List<WordBubbleState>,
    screenWidth: Dp,
    density: Density,
    textMeasurer: TextMeasurer,
) {
    var offsetX = 0.dp
    var offsetY = 8.dp

    states.firstOrNull()?.targetXOffset = offsetX
    states.firstOrNull()?.targetYOffset = offsetY

    states.drop(1).forEachIndexed { index, state ->
        val width = states[index].getWidth(density, textMeasurer)
        if (offsetX + width > screenWidth) {
            offsetX = 0.dp
            offsetY += state.getHeight(density, textMeasurer)
        } else offsetX += width

        state.targetXOffset = offsetX
        state.targetYOffset = offsetY
    }
}