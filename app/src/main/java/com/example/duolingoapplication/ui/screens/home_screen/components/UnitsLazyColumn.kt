package com.example.duolingoapplication.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.ui.screens.home_screen.models.UnitData


@Composable
fun UnitsLazyColumn(
    modifier : Modifier,
    state: LazyListState,
    units: List<UnitData>,
    starCountPerUnit: Int,
    onStarClicked : (coordinateInRoot : Float, isInteractive : Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        state = state,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        units.forEachIndexed { unitIndex, unit ->
            item {
                UnitHeader(
                    modifier = Modifier.fillMaxWidth(),
                    data = unit
                )
                Spacer(modifier = Modifier.height(48.dp))
                UnitContent(
                    unitIndex = unitIndex,
                    starCount = starCountPerUnit,
                    unitImage = unit.image,
                    colorMain = unit.color,
                    colorDark = unit.darkerColor,
                    onStarClicked = onStarClicked
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(400.dp))
        }
    }
}

