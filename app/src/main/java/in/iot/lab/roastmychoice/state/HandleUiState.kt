package `in`.iot.lab.roastmychoice.state

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import `in`.iot.lab.roastmychoice.view.animation.InternetErrorAnimation
import `in`.iot.lab.roastmychoice.view.animation.LoadingAnimation


@Composable
fun <T> UiState<T>.HandleUiState(
    idleBlock: @Composable (() -> Unit)? = null,
    loadingBlock: @Composable () -> Unit = { LoadingAnimation() },
    onTryAgain: () -> Unit,
    successBlock: @Composable ((T) -> Unit)? = null
) {

    val context = LocalContext.current

    when (this) {
        is UiState.Idle -> {
            idleBlock?.let { it() }
        }

        is UiState.Loading -> {
            loadingBlock()
        }

        is UiState.Success -> {
            successBlock?.let { it(data) }
        }

        is UiState.Error -> {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onTryAgain()
        }


        is UiState.NoInternetError -> {
            InternetErrorAnimation(onTryAgainClick = onTryAgain)
        }
    }
}