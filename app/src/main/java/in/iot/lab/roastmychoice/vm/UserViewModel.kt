package `in`.iot.lab.roastmychoice.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.data.repo.UserRepository
import `in`.iot.lab.roastmychoice.state.UiState
import `in`.iot.lab.roastmychoice.view.events.AppEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    private val _createUserState: MutableStateFlow<UiState<CreateUserResponse>> =
        MutableStateFlow(UiState.Idle)
    val createUserState = _createUserState.asStateFlow()

    private var userId: Int? = null
    private var domainId: Int? = null

    private fun createUser(name: String, rollNo: String, domainId: Int) {
        viewModelScope.launch {
            repository
                .createUser(CreateUserBody(name = name, rollNo = rollNo, domainId = domainId))
                .collect {
                    _createUserState.value = it

                    if (it is UiState.Success) {
                        userId = it.data.id
                        this@UserViewModel.domainId = it.data.domainId
                    }
                }
        }
    }

    private val _domainState: MutableStateFlow<UiState<GetDomainLevelsResponse>> =
        MutableStateFlow(UiState.Idle)
    val domainState = _domainState.asStateFlow()

    private fun fetchDomainData() {
        viewModelScope.launch {
            repository
                .getDomainLevels(domainId ?: 0)
                .collect {
                    _domainState.value = it
                }
        }
    }

    private val _createChoiceState: MutableStateFlow<UiState<CreateLevelChoiceResponse>> =
        MutableStateFlow(UiState.Idle)
    val createChoiceState = _createChoiceState.asStateFlow()

    private fun createChoice(levelId: Int, selected: Int) {
        viewModelScope.launch {
            repository
                .createLevelChoice(
                    CreateLevelChoiceBody(
                        levelId = levelId,
                        selected = selected,
                        userId = userId ?: 0
                    )
                )
                .collect {
                    _createChoiceState.value = it
                }
        }
    }


    private val _roastData: MutableStateFlow<UiState<GetAiModelResponse>> =
        MutableStateFlow(UiState.Idle)
    val roastData = _roastData.asStateFlow()

    private fun fetchRoastData() {
        viewModelScope.launch {
            repository
                .getAiResponse(userId ?: 0)
                .collect {
                    _roastData.value = it
                }
        }
    }

    private fun deleteUser() {
        viewModelScope.launch {
            repository
                .deleteUser(userId ?: 0)
                .collect {
                    // To be done later
                }
        }
    }

    fun uiListener(event: AppEvents) {

        when (event) {
            is AppEvents.CreateUser -> {
                createUser(name = event.name, rollNo = event.rollNo, domainId = event.domainId)
            }

            is AppEvents.ResetCreateState -> {
                _createUserState.value = UiState.Idle
            }

            is AppEvents.FetchDomainData -> {
                fetchDomainData()
            }

            is AppEvents.CreateLevelChoice -> {
                createChoice(levelId = event.levelId, selected = event.selected)
            }

            is AppEvents.FetchRoast -> {
                fetchRoastData()
            }

            is AppEvents.DeleteUser -> {
                deleteUser()
            }
        }
    }
}