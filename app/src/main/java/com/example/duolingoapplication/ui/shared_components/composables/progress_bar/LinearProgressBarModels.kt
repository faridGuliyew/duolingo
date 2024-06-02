package com.example.duolingoapplication.ui.shared_components.composables.progress_bar

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.ui.shared_components.models.ColorOptions
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.MaskGreen
import com.example.duolingoapplication.ui.theme.Swan


@Stable
class LinearProgressBarOptions(
    val cornerShape : RoundedCornerShape = CircleShape,
    val colors : ColorOptions = ColorOptions(),
    val progressHeightScale : Float = 0.25f,
    val progressStartPadding : Dp = 24.dp,
    val progressTopPadding : Dp = 6.dp
) {
    fun getCornerRadius(size: Size, density: Density) : CornerRadius {
        return CornerRadius(cornerShape.topStart.toPx(size, density), cornerShape.topStart.toPx(size, density))
    }
}

object LinearProgressBarDefaults {
    val linearProgressBarColorOptions = LinearProgressBarOptions(colors = ColorOptions(
        backgroundColor = Swan, primaryColor = FeatherGreen, secondaryColor = MaskGreen
    ))
}