package `in`.iot.lab.roastmychoice.data.remote

import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import `in`.iot.lab.roastmychoice.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ApiService {

    @POST(Constants.USER_CREATE_ENDPOINT)
    suspend fun createUser(
        @Body userDetails: CreateUserBody
    ): Response<CreateUserResponse>

    @POST(Constants.CREATE_CHOICE_ENDPOINT)
    suspend fun createLevelChoice(
        @Body levelChoiceDetails: CreateLevelChoiceBody
    ): Response<CreateLevelChoiceResponse>

    @GET(Constants.FETCH_DOMAIN_DATA_ENDPOINT)
    suspend fun getDomainLevels(
        @Path("domainId") domainId: Int
    ): Response<GetDomainLevelsResponse>

    @GET(Constants.FETCH_AI_RESPONSE_ENDPOINT)
    suspend fun getAiResponse(
        @Path("userId") userId: Int
    ): Response<GetAiModelResponse>

    @DELETE(Constants.DELETE_USER_ENDPOINT)
    suspend fun deleteUser(
        @Path("userId") userId: Int
    ): Response<Unit>
}