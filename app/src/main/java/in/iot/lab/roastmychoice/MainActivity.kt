package `in`.iot.lab.roastmychoice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import `in`.iot.lab.roastmychoice.view.navigation.AppNavigation
import `in`.iot.lab.roastmychoice.view.theme.RoastMyChoiceTheme
import `in`.iot.lab.roastmychoice.view.utils.setupSplashScreenExitAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private var isLoading = true

    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            delay(3000)
            isLoading = false
        }

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                isLoading
            }

            setOnExitAnimationListener { splashScreenViewProvider ->
                setupSplashScreenExitAnimation(splashScreenViewProvider)
            }
        }
        enableEdgeToEdge()
        setContent {
            RoastMyChoiceTheme {
                AppNavigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
