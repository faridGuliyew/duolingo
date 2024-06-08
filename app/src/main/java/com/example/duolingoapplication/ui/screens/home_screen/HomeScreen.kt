package com.example.duolingoapplication.ui.screens.home_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.ui.screens.home_screen.components.BottomBar
import com.example.duolingoapplication.ui.screens.home_screen.components.TopBar
import com.example.duolingoapplication.ui.screens.home_screen.components.UnitsLazyColumn
import com.example.duolingoapplication.ui.screens.home_screen.models.UnitData
import com.example.duolingoapplication.ui.shared_components.composables.text.PrimaryText
import com.example.duolingoapplication.ui.shared_components.composables.text.TitleText
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.Gray
import com.example.duolingoapplication.ui.theme.Polar
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(units: List<UnitData> = emptyList()) {
    val lazyListState = rememberLazyListState()
    val starCountPerUnit = 5
    /**
     * This property is used to animate status bar color
     */
    val visibleHeadingIndex by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex
        }
    }
    //dialog related states
    var isDialogShown by remember { mutableStateOf(false) }
    var isDialogInteractive by remember { mutableStateOf(false) }
    var dialogTranslation by remember { mutableFloatStateOf(0f) }
    var rootHeight by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopBar(
                units = units,
                visibleUnitIndex = visibleHeadingIndex
            )
        }, bottomBar = {
            BottomBar()
        }
    ) {
        UnitsLazyColumn(
            modifier = Modifier
                .padding(it)
                .onGloballyPositioned {
                    rootHeight = it.parentCoordinates!!.size.height.toFloat()
                }
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        isDialogShown = false
                    })
                },
            state = lazyListState,
            units = units,
            starCountPerUnit = starCountPerUnit
        ) { starCoordinate, isInteractive ->
            /**
             * isDialogInteractive stands for whether the dialog should contain a button or be LOCKED
             * We find the variance of the clicked button from the mid of the screen, and if is below mid point, animate to that point
             * After show, we show the dialog to the user
             */
            isDialogInteractive = isInteractive
            val midCoordinate = rootHeight / 2
            coroutineScope.launch {
                isDialogShown = false
                val scrollBy = (starCoordinate - midCoordinate).coerceAtLeast(0f)
                lazyListState.animateScrollBy(scrollBy)
                dialogTranslation = starCoordinate - scrollBy
                isDialogShown = true
            }
        }
    }

    StarDialog(
        isDialogShown = isDialogShown,
        isDialogInteractive = isDialogInteractive,
        dialogTranslation = dialogTranslation
    )

}

@Composable
fun StarDialog(
    isDialogShown : Boolean,
    isDialogInteractive : Boolean,
    dialogTranslation : Float
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        val animatedScale by animateFloatAsState(targetValue = if (isDialogShown) 1f else 0f)
        Column(
            modifier = Modifier
                .graphicsLayer {
                    translationY = dialogTranslation + 100.dp.toPx()
                    transformOrigin = TransformOrigin(0.5f, 0f)
                    scaleY = animatedScale
                    scaleX = animatedScale
                }
                .fillMaxWidth(0.8f)
                .background(if (isDialogInteractive.not()) Polar else FeatherGreen, shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Gray, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TitleText(text = "Make introductions", color = if (isDialogInteractive.not()) DarkGray.copy(0.5f) else Color.White, fontSize = 19.sp)
            PrimaryText(
                text = "Complete all levels above to unlock this",
                color = if (isDialogInteractive.not()) DarkGray.copy(0.3f) else Color.White
            )
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDialogInteractive.not()) DarkGray.copy(0.15f) else Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                TitleText(
                    text =  if (isDialogInteractive.not()) "LOCKED" else "LETS GO!",
                    color = if (isDialogInteractive.not()) DarkGray.copy(0.5f) else FeatherGreen,
                    fontSize = 18.sp
                )
            }
        }

    }
}


//fun to calculate start ordering, nothing fancy
fun orderToPercentage(order: Int, isRTL: Boolean = true): Float {
    val difference = 0.09f
    return when (order) {
        0 -> 0.45f
        1 -> 0.45f - if (isRTL) difference else -difference
        2 -> 0.45f - if (isRTL) difference * 2 else -difference * 2
        3 -> 0.45f - if (isRTL) difference else -difference
        4 -> 0.45f
        else -> 0.45f
    }
}


@Preview
@Composable
private fun HomeScreenPrev() {
    val units = remember {
        listOf(
            UnitData(title = "Unit 1", color = FeatherGreen),
            UnitData(title = "Unit 2", color = Color.Red, darkerColor = Color.Red),
            UnitData(title = "Unit 3", color = Color.Yellow),
            UnitData(title = "Unit 4", color = Color.Gray),
            UnitData(title = "Unit 5", color = Color.Magenta),
            UnitData(title = "Unit 6", color = Color.Blue)
        )
    }
    HomeScreen(
        units = units
    )

}
