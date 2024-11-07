package `in`.iot.lab.roastmychoice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import `in`.iot.lab.roastmychoice.ui.theme.RoastMyChoiceTheme
import `in`.iot.lab.roastmychoice.view.navigation.AppNavigation
import `in`.iot.lab.roastmychoice.view.screens.OnboardingScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoastMyChoiceTheme {
                AppNavigation()
            }
        }
    }
}