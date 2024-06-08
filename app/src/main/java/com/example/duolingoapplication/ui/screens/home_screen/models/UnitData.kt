package com.example.duolingoapplication.ui.screens.home_screen.models

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.FeatherGreenDark

@Immutable
data class UnitData(
    val color: Color = FeatherGreen,
    val darkerColor: Color = FeatherGreenDark,
    val title: String = "Unit 1",
    val description: String = "Make introductions",
    val image : Int = R.drawable.ic_duo_main
)