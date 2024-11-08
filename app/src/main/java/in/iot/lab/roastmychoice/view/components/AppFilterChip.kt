package `in`.iot.lab.roastmychoice.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AppFilterChip(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    FilterChip(
        selected = isSelected,
        onClick = onSelected,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = Color.White,
            selectedContainerColor = Color(0xFF7B0FFF)
        ),

        label = {

            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                text = text,
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Medium
            )
        }
    )
}