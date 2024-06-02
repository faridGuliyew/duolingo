package com.example.duolingoapplication.ui.shared_components.composables.top_app_bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.duolingoapplication.ui.shared_components.composables.progress_bar.LinearProgressBar
import com.example.duolingoapplication.ui.theme.Hare

@Composable
fun QuizTopAppBar(progress : Float) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            tint = Hare,
            contentDescription = "close"
        )
        Spacer(modifier = Modifier.width(16.dp))
        LinearProgressBar(modifier = Modifier.weight(1f).height(24.dp),progress = progress)
    }
}