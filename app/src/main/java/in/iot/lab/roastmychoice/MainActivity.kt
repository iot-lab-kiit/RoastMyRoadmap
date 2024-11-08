package `in`.iot.lab.roastmychoice

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import `in`.iot.lab.roastmychoice.view.theme.RoastMyChoiceTheme
import `in`.iot.lab.roastmychoice.view.navigation.AppNavigation

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

    override fun onDestroy() {
        super.onDestroy()

    }
}
