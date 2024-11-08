package `in`.iot.lab.roastmychoice.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import `in`.iot.lab.roastmychoice.view.screens.AiPromptScreen
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen
import `in`.iot.lab.roastmychoice.view.screens.Questions

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.OnBoardingScreen.route
    ) {

        //add composable here
        composable(AppScreens.OnBoardingScreen.route) {
            OnboardingScreen(navController)
        }

        val questionScreenRoute = AppScreens.QuestionsScreen.route
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
                            navController = navController,
                            userId = userId,
                            domainId = domainId
                        )
                    }

                }
            }
        }

        val aiPromptScreenRoute = AppScreens.AiPromptScreen.route
        composable("$aiPromptScreenRoute/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("userId").let {
                if (it != null) {
                    AiPromptScreen(navController = navController, userId = it)
                }
            }
        }
//        composable(AppScreens.AiPromptScreen.route){
//            AiPromptScreen(navController)
//        }
    }
}