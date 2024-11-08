package `in`.iot.lab.roastmychoice.view.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import `in`.iot.lab.roastmychoice.view.screens.AiPromptScreen
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen
import `in`.iot.lab.roastmychoice.view.screens.Questions
import `in`.iot.lab.roastmychoice.vm.UserViewModel

const val ONBOARDING_SCREEN = "onboarding_screen"
const val QUESTION_SCREEN = "questions_screen"
const val AI_PROMPT_SCREEN = "ai_prompt_screen"


@Composable
fun AppNavigation(
    viewModel: UserViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ONBOARDING_SCREEN
    ) {

        //On Boarding Screen
        composable(ONBOARDING_SCREEN) {
            OnboardingScreen(navController, viewModel)
        }

        // Question Screen
        val questionScreenRoute = QUESTION_SCREEN
        composable("$questionScreenRoute/{userId}/{domainId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.IntType
                },
                navArgument(name = "domainId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            backStackEntry.arguments?.let {
                it.getInt("userId").let { userId ->
                    it.getInt("domainId").let { domainId ->
                        Questions(
                            viewModel = viewModel,
                            navController = navController,
                            userId = userId,
                            domainId = domainId
                        )
                    }

                }
            }
        }

        val aiPromptScreenRoute = AI_PROMPT_SCREEN
        composable("$aiPromptScreenRoute/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("userId").let {
                if (it != null) {
                    AiPromptScreen(
                        navController = navController,
                        userId = it,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}