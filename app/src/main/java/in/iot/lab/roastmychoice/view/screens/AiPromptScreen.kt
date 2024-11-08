package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.state.UiState
import `in`.iot.lab.roastmychoice.view.components.AppScreen
import `in`.iot.lab.roastmychoice.view.components.PrimaryButton
import `in`.iot.lab.roastmychoice.view.theme.GradientBrush
import `in`.iot.lab.roastmychoice.view.events.AppEvents
import `in`.iot.lab.roastmychoice.view.navigation.ONBOARDING_SCREEN


@Composable
fun AiPromptScreen(
    roastDataState: UiState<GetAiModelResponse>,
    navController: NavController,
    setEvent: (AppEvents) -> Unit
) {

    AppScreen {
        when (roastDataState) {
            is UiState.Idle -> {
                setEvent(AppEvents.FetchRoast)
            }

            is UiState.Loading -> {
                Text(text = "Loading")
            }

            is UiState.Success -> {
                AiPromptScreenIdle(
                    roastData = roastDataState.data,
                    navController = navController,
                    setEvent = setEvent
                )
            }

            else -> {
                Text(text = "Network Error")
            }
        }
    }
}


@Composable
fun AiPromptScreenIdle(
    roastData: GetAiModelResponse,
    navController: NavController,
    setEvent: (AppEvents) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 24.dp,
            alignment = Alignment.CenterVertically
        ),
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {

        // Ai Generated Response
        OutlinedCard(
            modifier = Modifier.padding(24.dp),
            onClick = {},
            border = BorderStroke(
                width = Dp.Hairline,
                brush = GradientBrush
            )
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = roastData.data
            )
        }

        // Try Again Button
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = "Try Again"
        ) {
            setEvent(AppEvents.DeleteUser)
            navController.navigate(ONBOARDING_SCREEN)
        }
    }
}