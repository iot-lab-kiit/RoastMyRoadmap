package `in`.iot.lab.roastmychoice.data.repo

import android.util.Log
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.data.remote.ApiService
import `in`.iot.lab.roastmychoice.data.utils.UiState
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

    suspend fun getDomainLevels(domainId: Int) : UiState<GetDomainLevelsResponse> {
        val response = api.getDomainLevels(domainId)
        if (response.isSuccessful)
            return UiState.Success(response.body()!!)
        return UiState.Error(response.message())
    }

    suspend fun getAiResponse(userId: Int) : UiState<GetAiModelResponse> {
        val response = api.getAiResponse(userId)
        if (response.isSuccessful){
            Log.d("UserRepository", "getAiResponse: ${response.body()}")

            return UiState.Success(response.body()!!)
            }
        return UiState.Error(response.message())
    }
}