package `in`.iot.lab.roastmychoice.view.screens

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.state.HandleUiState
import `in`.iot.lab.roastmychoice.state.UiState
import `in`.iot.lab.roastmychoice.view.components.AppFilterChip
import `in`.iot.lab.roastmychoice.view.components.AppScreen
import `in`.iot.lab.roastmychoice.view.components.AppTextField
import `in`.iot.lab.roastmychoice.view.components.OnBoardingImage
import `in`.iot.lab.roastmychoice.view.components.PrimaryButton
import `in`.iot.lab.roastmychoice.view.events.AppEvents
import `in`.iot.lab.roastmychoice.view.navigation.QUESTION_SCREEN


@Composable
fun OnboardingScreen(
    navController: NavController,
    createUserState: UiState<CreateUserResponse>,
    setEvent: (AppEvents) -> Unit
) {

    val activity = LocalContext.current as Activity
    BackHandler {
        activity.finish()
    }

    AppScreen {
        createUserState.HandleUiState(
            idleBlock = {
                OnBoardingScreenIdle(setEvent)
            },
            successBlock = {
                navController.navigate(QUESTION_SCREEN)
                setEvent(AppEvents.ResetCreateState)
            },
            onTryAgain = {
                setEvent(AppEvents.ResetCreateState)
            }
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun OnBoardingScreenIdle(
    setEvent: (AppEvents) -> Unit
) {

    // User Input Fields
    var name by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var selectedChip by remember { mutableIntStateOf(0) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF041529)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp, alignment = Alignment.CenterVertically)
    ) {

        // Innovance Logo
        OnBoardingImage()

        // User ID Text Field with Gradient Border Only
        AppTextField(
            value = name,
            title = "Enter your name",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            onValueChange = { name = it }
        )

        // Roll Number
        AppTextField(
            value = rollNo,
            title = "Enter Roll Number",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
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
        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Get Started"
        ) {
            if (name.isEmpty() || rollNo.isEmpty() || selectedChip == 0)
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            else
                setEvent(
                    AppEvents.CreateUser(
                        name = name,
                        rollNo = rollNo,
                        domainId = selectedChip
                    )
                )
        }
    }
}