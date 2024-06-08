package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.R
import com.example.duolingoapplication.ui.screens.home_screen.models.UnitData
import com.example.duolingoapplication.ui.shared_components.composables.text.PrimaryText
import com.example.duolingoapplication.ui.shared_components.composables.text.TitleText

@Composable
@Preview
fun UnitHeader(
    modifier: Modifier = Modifier,
    data: UnitData = UnitData()
) {
    Row(
        modifier = modifier
            .background(data.color)
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp, bottom = 18.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            TitleText(
                text = data.title,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryText(
                text = data.description,
                color = Color.White,
                fontSize = 18.sp
            )
        }
        Box (
            modifier = Modifier
                .background(color = data.darkerColor, shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 1.5.dp)
                .padding(top = 1.5.dp, bottom = 3.dp)
        ) {
            Box (
                modifier = Modifier
                    .background(color = data.color, shape = RoundedCornerShape(10.dp))
                    .padding(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_notebook_fill   ),
                    tint = Color.White,
                    contentDescription = "more"
                )
            }
        }
    }
}