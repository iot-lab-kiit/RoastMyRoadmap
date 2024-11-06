package `in`.iot.lab.roastmychoice.data.repo

import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.remote.ApiService
import `in`.iot.lab.roastmychoice.data.utils.UiState
import iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: ApiService) {

    suspend fun createUser(userDetails : CreateUserBody) : UiState<CreateUserResponse> {
        val response = api.createUser(userDetails)
        if (response.isSuccessful)
            return UiState.Success(response.body()!!)
        return UiState.Error(response.message())
    }

    suspend fun createLevelChoice(choiceDetails: CreateLevelChoiceBody) : UiState<CreateLevelChoiceResponse> {
        val response = api.createLevelChoice(choiceDetails)
        if (response.isSuccessful)
            return UiState.Success(response.body()!!)
        return UiState.Error(response.message())
    }
}