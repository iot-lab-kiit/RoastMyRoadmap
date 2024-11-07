package `in`.iot.lab.roastmychoice.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen
import `in`.iot.lab.roastmychoice.view.screens.RoastScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = AppScreens.OnBoardingScreen.route) {

        //add composable here
        composable(route = AppScreens.OnBoardingScreen.route){
            OnboardingScreen {

            }
        }

        composable(route = AppScreens.AiPromptScreen.route){
            RoastScreen()
        }
    }
}