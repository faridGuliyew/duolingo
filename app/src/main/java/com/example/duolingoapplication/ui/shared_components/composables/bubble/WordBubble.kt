package com.example.duolingoapplication.ui.shared_components.composables.bubble

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duolingoapplication.ui.shared_components.composables.text.PrimaryText
import com.example.duolingoapplication.ui.theme.Hare

//@Composable
//fun WordBubble(text : String) {
//    Box  (
//        modifier = Modifier.padding(12.dp)
//    ) {
//        //background
//        Box(
//            modifier = Modifier
//                .clip(RoundedCornerShape(8.dp))
//                .background(Hare)
//                .padding(horizontal = 8.dp)
//                .padding(vertical = 6.dp)
//        ) {
//            PrimaryText(text = "thank you", color = Color.Transparent)
//        }
//        //main
//        Box(
//            modifier = Modifier
//                .clip(RoundedCornerShape(8.dp))
//                .border(
//                    color = Hare,
//                    width = 1.dp,
//                    shape = RoundedCornerShape(8.dp)
//                )
//                .padding(horizontal = 8.dp)
//                .padding(vertical = 6.dp)
//        ) {
//            PrimaryText(text = text)
//        }
//    }
//}

@Composable
fun WordBubble(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(
        modifier = modifier
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        //val animatedScale by animateFloatAsState(targetValue = if (isPressed) 0f else 1f)
        //main
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Hare)
                .clickable(interactionSource = interactionSource, indication = null) {
                    onClick()
                }
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .border(
                        color = Hare,
                        width = 1.dp,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 10.dp)
            ) {
                PrimaryText(text = text, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
//            Spacer(
//                modifier = Modifier.height(2.dp * animatedScale)
//            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WordBubblePrev() {
    WordBubble(text = "thank you"){}
}