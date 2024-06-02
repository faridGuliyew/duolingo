package com.example.duolingoapplication.ui.shared_components.composables.text

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.ui.theme.museoSansFamily

@Composable
fun TitleText(@StringRes text : Int, color : Color = Color.Black) {
    Text(
        text = stringResource(id = text),
        color = color,
        fontFamily = museoSansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp
    )
}

@Composable
fun TitleText(text : String, color : Color = Color.Black) {
    Text(
        text = text,
        color = color,
        fontFamily = museoSansFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 22.sp
    )
}