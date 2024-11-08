package `in`.iot.lab.roastmychoice.state

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data object NoInternetError : UiState<Nothing>
    class Success<T>(val data: T) : UiState<T>
    class Error(val message: String) : UiState<Nothing>
}