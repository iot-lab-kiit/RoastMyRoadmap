package `in`.iot.lab.roastmychoice.view.components

import androidx.compose.runtime.Composable
import `in`.iot.lab.roastmychoice.view.screens.ClickableChip

@Composable
fun DomainOptionUI(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    ClickableChip(
        text = text,
        isSelected = isSelected,
        onClick = onSelected
    )
}