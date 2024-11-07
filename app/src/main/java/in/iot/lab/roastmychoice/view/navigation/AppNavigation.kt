package `in`.iot.lab.roastmychoice.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.iot.lab.roastmychoice.view.screens.AiPromptScreen
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.OnBoardingScreen.route) {

        //add composable here
        composable(AppScreens.OnBoardingScreen.route){
            OnboardingScreen(navController)
        }

        composable(AppScreens.AiPromptScreen.route){
            AiPromptScreen()
        }
    }
}