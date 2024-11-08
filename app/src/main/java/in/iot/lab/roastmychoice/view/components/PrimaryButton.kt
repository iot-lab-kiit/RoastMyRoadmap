package `in`.iot.lab.roastmychoice.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.iot.lab.roastmychoice.ui.theme.GradientBrush


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {

    // Primary Button
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                brush = GradientBrush,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues()
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = text,
            color = Color.White,
            fontSize = 22.sp
        )
    }
}