package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.FeatherGreenDark
import com.example.duolingoapplication.ui.theme.Gallery
import com.example.duolingoapplication.ui.theme.Gray
import com.example.duolingoapplication.ui.theme.GrayDark
import com.example.duolingoapplication.ui.theme.Hare
import com.example.duolingoapplication.ui.theme.Swan
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun StarButton(
    onStarClicked: (coordinateInRoot: Float, isInteractive : Boolean) -> Unit,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    //val isClicked by interactionSource.collectIsPressedAsState()
    var isClicked by remember {
        mutableStateOf(false)
    }
    val animatedTranslation by animateFloatAsState(
        targetValue = if (isClicked) 0f else -23f,
        animationSpec = tween(80)
    )
    var positionInRoot by remember {
        mutableFloatStateOf(0f)
    }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .animateContentSize()
            .onGloballyPositioned {
                positionInRoot = it.positionInRoot().y
            }
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer {
                    rotationX = 30f
                }
                .padding(12.dp)
                .background(color = GrayDark, shape = CircleShape)
        ) {
            Image(
                modifier = Modifier
                    .width(72.dp)
                    .graphicsLayer {
                        translationY = animatedTranslation
                    }
                    .padding(0.dp)
                    .background(color = Gray, shape = CircleShape)
                    .padding(20.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            scope.launch {
                                isClicked = true
                                delay(80)
                                onStarClicked(positionInRoot, false)
                                isClicked = false
                            }
                        }
                    }.pointerInput(Unit) {
                        detectDragGesturesAfterLongPress (
                            onDragStart = {
                                isClicked = true
                            }, onDragEnd = {
                                isClicked = false
                                onStarClicked(positionInRoot, false)
                            }
                        ) { _,_-> }
                    }

                ,
                painter = painterResource(id = R.drawable.ic_star),
                colorFilter = ColorFilter.tint(color = GrayDark),
                contentDescription = "star"
            )
        }
    }
}
