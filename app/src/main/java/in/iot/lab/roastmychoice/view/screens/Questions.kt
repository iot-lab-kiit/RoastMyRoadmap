package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.R
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.state.UiState
import `in`.iot.lab.roastmychoice.view.components.AppScreen
import `in`.iot.lab.roastmychoice.view.components.PrimaryButton
import `in`.iot.lab.roastmychoice.view.events.AppEvents
import `in`.iot.lab.roastmychoice.view.navigation.AI_PROMPT_SCREEN


@Composable
fun Questions(
    domainDataState: UiState<GetDomainLevelsResponse>,
    navController: NavController,
    setEvent: (AppEvents) -> Unit
) {

    AppScreen {

        when (domainDataState) {
            is UiState.Idle -> {
                setEvent(AppEvents.FetchDomainData)
            }

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                QuestionScreenIdle(
                    domainData = domainDataState.data,
                    navController = navController,
                    setEvent = setEvent
                )
            }

            is UiState.NoInternetError -> {

            }

            is UiState.Error -> {

            }
        }
    }
}


@Composable
fun QuestionScreenIdle(
    domainData: GetDomainLevelsResponse,
    navController: NavController,
    setEvent: (AppEvents) -> Unit
) {

    // Current Question Index
    var currentLevelIndex by remember { mutableIntStateOf(0) }

    // Current Question
    val currentLevel = domainData.levels[currentLevelIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Innovance Logo
        Image(
            painter = painterResource(id = R.drawable.innovance_text),
            contentDescription = null,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .size(200.dp)
        )

        // Question
        Text(
            modifier = Modifier.padding(16.dp),
            text = currentLevel.question,
            color = Color.Black
        )

        // Options
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(colorResource(id = R.color.bluescreen)),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(currentLevel.options.size) { index ->

                // Current Option
                val option = currentLevel.options[index]

                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = option
                ) {

                    setEvent(
                        AppEvents.CreateLevelChoice(
                            levelId = currentLevel.id,
                            selected = index
                        )
                    )

                    // Forwarding to the next Level
                    if (currentLevelIndex < domainData.levels.size - 1)
                        currentLevelIndex++
                    else
                        navController.navigate(AI_PROMPT_SCREEN)
                }
            }
        }
    }
}