package com.example.duolingoapplication.ui.shared_components.composables.misc

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.theme.Beetle

@Composable
@Preview
fun NewWordBubble() {
    Image(
        modifier = Modifier.size(24.dp)
            .clip(CircleShape)
            .background(Beetle)
            .padding(4.dp),
        painter = painterResource(id = R.drawable.ic_bubble),
        colorFilter = ColorFilter.tint(Color.White),
        contentDescription = "bubble"
    )
}