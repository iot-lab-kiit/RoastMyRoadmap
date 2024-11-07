package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun RoastScreen() {


    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .border(
                        border = BorderStroke(
                            width = Dp.Hairline,
                            brush = GradientBrush
                        )
                    )
//                    .clip()
            ) {
                Text(
                    text = "\n" +
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam semper mattis tortor sit amet convallis. Ut eget tincidunt nisi, quis aliquet velit. Mauris quis purus elementum, tincidunt metus id, finibus ante. Ut a efficitur libero. Aliquam id justo at nulla vehicula congue in in metus. Cras eget lacus lectus. Curabitur."
                )
            }
        }
    }
}