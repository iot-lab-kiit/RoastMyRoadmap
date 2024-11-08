package `in`.iot.lab.roastmychoice.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.data.repo.UserRepository
import `in`.iot.lab.roastmychoice.data.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _createUserState: MutableStateFlow<UiState<CreateUserResponse>> = MutableStateFlow(UiState.Idle)
    val createUserState = _createUserState.asStateFlow()

    fun resetCreateUserState() {
        _createUserState.value = UiState.Idle
    }

    fun createUser(userDetails : CreateUserBody) {
        createUserDB(userDetails)
    }

    private fun createUserDB(userDetails : CreateUserBody) {
        _createUserState.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = repository.createUser(userDetails)
                _createUserState.value = response
            }
            catch (e: Exception) {
                Log.d("UserViewModel", "createUserDetails: ${e.message}")
            }
        }
    }


    private val _createChoiceState: MutableStateFlow<UiState<CreateLevelChoiceResponse>> = MutableStateFlow(UiState.Idle)
    val createChoiceState = _createChoiceState.asStateFlow()

    fun createChoice(choiceDetails : CreateLevelChoiceBody) {
        createChoiceDB(choiceDetails)
    }

    private fun createChoiceDB(choiceDetails : CreateLevelChoiceBody) {
        _createChoiceState.value = UiState.Loading

        viewModelScope.launch {
            try {
                _createChoiceState.value = repository.createLevelChoice(choiceDetails)
            } catch (e: Exception) {
                Log.d("UserViewModel", "createUserChoices: ${e.message}")
//                _createChoiceState.value = UiState.Error(e.message.toString())
            }
        }
    }

    private val _getDomainLevelsState: MutableStateFlow<UiState<GetDomainLevelsResponse>> = MutableStateFlow(UiState.Idle)
    val getDomainLevelsState = _getDomainLevelsState.asStateFlow()

    fun getDomainLevels(domainId: Int) {
        getDomainLevelsDB(domainId)
    }

    private fun getDomainLevelsDB(domainId: Int) {
        _getDomainLevelsState.value = UiState.Loading

        viewModelScope.launch {
            try {
                _getDomainLevelsState.value = repository.getDomainLevels(domainId)
            } catch (e: Exception) {
                Log.d("UpdateDetailsViewModel", "getDomainLevels: ${e.message}")
//                _createUserState.value = UiState.Error(e.message.toString())
            }
        }
    }
}