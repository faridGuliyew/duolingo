package com.example.duolingoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.example.duolingoapplication.ui.screens.home_screen.HomeScreen
import com.example.duolingoapplication.ui.screens.home_screen.models.UnitData
import com.example.duolingoapplication.ui.theme.Blue
import com.example.duolingoapplication.ui.theme.BlueDark
import com.example.duolingoapplication.ui.theme.Cyan
import com.example.duolingoapplication.ui.theme.CyanDark
import com.example.duolingoapplication.ui.theme.FeatherGreen
import com.example.duolingoapplication.ui.theme.FeatherGreenDark
import com.example.duolingoapplication.ui.theme.Pink
import com.example.duolingoapplication.ui.theme.PinkDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val units = remember {
                listOf(
                    UnitData(title = "Unit 1", color = FeatherGreen, darkerColor = FeatherGreenDark, image = R.drawable.ic_duo_main),
                    UnitData(title = "Unit 2", color = Pink, darkerColor = PinkDark, image = R.drawable.duo_kid),
                    UnitData(title = "Unit 3", color = Cyan, darkerColor = CyanDark, image = R.drawable.duo_eddy),
                    UnitData(title = "Unit 4", color = FeatherGreen, darkerColor = FeatherGreenDark, image = R.drawable.duo_lgbt),
                    UnitData(title = "Unit 5", color = Blue, darkerColor = BlueDark, image = R.drawable.duo_ship)
                )
            }
            HomeScreen(units = units)
        }
    }
}
