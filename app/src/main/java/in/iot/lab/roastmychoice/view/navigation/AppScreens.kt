package `in`.iot.lab.roastmychoice.view.navigation

sealed class AppScreens(val route: String) {
    object OnBoardingScreen : AppScreens("onboarding_screen")
    object QuestionsScreen : AppScreens("questions_screen")
    object AiPromptScreen : AppScreens("ai_prompt_screen")
}