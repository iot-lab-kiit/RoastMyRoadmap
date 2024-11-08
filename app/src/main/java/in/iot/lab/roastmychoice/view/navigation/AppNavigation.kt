package `in`.iot.lab.roastmychoice.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.iot.lab.roastmychoice.view.screens.AiPromptScreen
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen
import `in`.iot.lab.roastmychoice.view.screens.Questions
import `in`.iot.lab.roastmychoice.vm.UserViewModel


internal const val ONBOARDING_SCREEN = "onboarding_screen"
internal const val QUESTION_SCREEN = "questions_screen"
internal const val AI_PROMPT_SCREEN = "ai_prompt_screen"


@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    viewModel: UserViewModel = hiltViewModel()
) {

    NavHost(
        navController = navController,
        startDestination = ONBOARDING_SCREEN
    ) {

        //On Boarding Screen
        composable(ONBOARDING_SCREEN) {

            val createUserState = viewModel.createUserState.collectAsState().value
            OnboardingScreen(
                navController = navController,
                createUserState = createUserState,
                setEvent = viewModel::uiListener
            )
        }

        // Question Screen
        composable(QUESTION_SCREEN) {

            val domainDataState = viewModel.domainState.collectAsState().value
            val choiceState = viewModel.createChoiceState.collectAsState().value
            val deleteState = viewModel.deleteState.collectAsState().value

            Questions(
                domainDataState = domainDataState,
                choiceState = choiceState,
                deleteState = deleteState,
                navController = navController,
                setEvent = viewModel::uiListener
            )
        }

        // AI Roast Screen
        composable(AI_PROMPT_SCREEN) {

            val roastDataState = viewModel.roastData.collectAsState().value
            val deleteUserState = viewModel.deleteState.collectAsState().value
            AiPromptScreen(
                roastDataState = roastDataState,
                deleteUserState = deleteUserState,
                navController = navController,
                setEvent = viewModel::uiListener
            )
        }
    }
}