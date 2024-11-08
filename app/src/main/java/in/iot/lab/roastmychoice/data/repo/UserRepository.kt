package `in`.iot.lab.roastmychoice.data.repo

import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.data.remote.ApiService
import `in`.iot.lab.roastmychoice.data.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: ApiService) {

    suspend fun createUser(userDetails: CreateUserBody): Flow<UiState<CreateUserResponse>> {
        return flow {
            try {

                emit(UiState.Loading)

                val response = api.createUser(userDetails)
                if (response.isSuccessful)
                    emit(UiState.Success(response.body()!!))
                else
                    emit(UiState.Error(response.message()))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }

    suspend fun createLevelChoice(choiceDetails: CreateLevelChoiceBody): Flow<UiState<CreateLevelChoiceResponse>> {
        return flow {
            try {

                emit(UiState.Loading)

                val response = api.createLevelChoice(choiceDetails)
                if (response.isSuccessful)
                    emit(UiState.Success(response.body()!!))
                else
                    emit(UiState.Error(response.message()))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }

    suspend fun getDomainLevels(domainId: Int): Flow<UiState<GetDomainLevelsResponse>> {
        return flow {
            try {

                emit(UiState.Loading)

                val response = api.getDomainLevels(domainId)
                if (response.isSuccessful)
                    emit(UiState.Success(response.body()!!))
                else
                    emit(UiState.Error(response.message()))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }

    suspend fun getAiResponse(userId: Int): Flow<UiState<GetAiModelResponse>> {
        return flow {
            try {

                emit(UiState.Loading)

                val response = api.getAiResponse(userId)
                if (response.isSuccessful)
                    emit(UiState.Success(response.body()!!))
                else
                    emit(UiState.Error(response.message()))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }

        }
    }

    suspend fun deleteUser(userId: Int): Flow<UiState<Unit>> {
        return flow {
            try {

                emit(UiState.Loading)

                val response = api.deleteUser(userId)
                if (response.isSuccessful)
                    emit(UiState.Success(response.body()!!))
                else
                    emit(UiState.Error(response.message()))
            } catch (e: Exception) {
                emit(UiState.Error(e.message.toString()))
            }
        }
    }
}