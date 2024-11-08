package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.data.utils.UiState
import `in`.iot.lab.roastmychoice.vm.UserViewModel


@Composable
fun AiPromptScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel(),
    userId: Int
) {


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
                val data = viewModel.aiResponseState.collectAsState().value
                when (data) {
                    is UiState.Idle -> {
                        viewModel.getAiResponse(userId)
                    }

                    is UiState.Loading -> {
                        Text(text = "Loading")
                    }

                    is UiState.Success -> {
                        Text(text = data.data.data)
                    }

                    else -> {
                        Text(text = "Network Error")
                    }
                }
            }

        }
    }
}
