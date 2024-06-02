package com.example.duolingoapplication.ui.shared_components.composables.progress_bar

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.ui.shared_components.models.ColorOptions
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.MaskGreen
import com.example.duolingoapplication.ui.theme.Swan

@Composable
fun LinearProgressBar(
    modifier: Modifier = Modifier,
    options: LinearProgressBarOptions = LinearProgressBarDefaults.linearProgressBarColorOptions,
    progress : Float
) {
    val animatedProgress by animateFloatAsState(targetValue = progress)
    val density = LocalDensity.current
    Canvas(modifier = modifier
        .clip(options.cornerShape)
        .background(options.colors.backgroundColor)) {
        drawRoundRect(
            color = options.colors.primaryColor,
            size = Size(height = size.height, width = size.width * animatedProgress),
            cornerRadius = options.getCornerRadius(size, density)
        )

        if (progress != 0f)
        drawRoundRect(
            color = options.colors.secondaryColor,
            topLeft = Offset(options.progressStartPadding.toPx(), options.progressTopPadding.toPx()),
            size = Size(height = size.height * options.progressHeightScale, width = size.width * animatedProgress - options.progressStartPadding.toPx() * 2),
            cornerRadius = options.getCornerRadius(size, density)
        )
    }

}

@Preview (showBackground = true)
@Composable
private fun LinearProgressBarPrev() {
    LinearProgressBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        options = LinearProgressBarOptions(colors = ColorOptions(
            backgroundColor = Swan, primaryColor = FeatherGreen, secondaryColor = MaskGreen
        )), progress = 0f
    )
}