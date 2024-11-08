package `in`.iot.lab.roastmychoice.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.utils.UiState
import `in`.iot.lab.roastmychoice.view.components.AppTextField
import `in`.iot.lab.roastmychoice.view.components.AppFilterChip
import `in`.iot.lab.roastmychoice.view.components.AppScreen
import `in`.iot.lab.roastmychoice.view.components.OnBoardingImage
import `in`.iot.lab.roastmychoice.view.components.PrimaryButton
import `in`.iot.lab.roastmychoice.view.navigation.AppScreens
import `in`.iot.lab.roastmychoice.vm.UserViewModel


@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: UserViewModel
) {

    val data = viewModel.createUserState.collectAsState().value

    AppScreen {

        when (data) {

            is UiState.Idle -> {
                OnBoardingScreenIdle(viewModel)
            }

            is UiState.Loading -> {
                CircularProgressIndicator()
            }

            is UiState.Success -> {
                val userId = data.data.id
                val domainId = data.data.domainId

                navController.navigate(AppScreens.QuestionsScreen.route + "/${userId}/${domainId}")
                viewModel.resetCreateUserState()
            }

            else -> {

            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnBoardingScreenIdle(
    viewModel: UserViewModel
) {

    // User Input Fields
    var name by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var selectedChip by remember { mutableIntStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF041529)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // Innovance Logo
        OnBoardingImage()

        // User ID Text Field with Gradient Border Only
        AppTextField(
            value = name,
            title = "User name",
            onValueChange = { name = it }
        )

        // Roll Number
        AppTextField(
            value = rollNo,
            title = "Roll Number",
            onValueChange = { rollNo = it }
        )

        // Domain UI
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            // List of Domains.
            listOf(
                Pair(1, "App Dev"), Pair(2, "IoT"),
                Pair(3, "Web Dev"), Pair(4, "CP"),
                Pair(5, "Cybersecurity"), Pair(6, "ML")
            ).forEach { (id, text) ->

                AppFilterChip(
                    text = text,
                    isSelected = selectedChip == id,
                    onSelected = { selectedChip = id }
                )
            }
        }

        // Primary Button
        PrimaryButton(text = "Get Started") {
            viewModel.createUser(
                userDetails = CreateUserBody(
                    domainId = selectedChip,
                    name = name,
                    rollNo = rollNo
                )
            )
        }
    }
}