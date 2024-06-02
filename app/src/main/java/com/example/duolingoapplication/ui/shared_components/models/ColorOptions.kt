package com.example.duolingoapplication.ui.shared_components.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
class ColorOptions (
    val backgroundColor : Color = Color.Unspecified,
    val primaryColor: Color = Color.Unspecified,
    val secondaryColor: Color = Color.Unspecified
)