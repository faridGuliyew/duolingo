package com.example.duolingoapplication.ui.shared_components.composables.text

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.Beetle
import com.example.duolingoapplication.ui.theme.museoSansFamily

@Composable
fun PrimaryText(@StringRes text : Int, color : Color = Color.Black, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = stringResource(id = text),
        color = color,
        fontFamily = museoSansFamily,
        fontWeight = fontWeight
    )
}

@Composable
fun PrimaryText(text : String, color : Color = Color.Black, fontSize : TextUnit = 14.sp, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = text,
        color = color,
        fontFamily = museoSansFamily,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}