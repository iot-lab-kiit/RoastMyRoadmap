package `in`.iot.lab.roastmychoice.data.remote

import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.GetAiModelResponse
import `in`.iot.lab.roastmychoice.data.model.GetDomainLevelsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ApiService {

    @POST("users")
    suspend fun createUser(
        @Body userDetails : CreateUserBody
    ) : Response<CreateUserResponse>

    @POST("user/choices")
    suspend fun createLevelChoice(
        @Body levelChoiceDetails : CreateLevelChoiceBody
    ) : Response<CreateLevelChoiceResponse>

    @GET("domains/{domainId}")
    suspend fun getDomainLevels(
        @Path("domainId") domainId: Int
    ): Response<GetDomainLevelsResponse>

    @GET("api/v1/openai-response/{userId}")
    suspend fun getAiResponse(
        @Path("userId") userId: Int
    ): Response<GetAiModelResponse>

    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Path("userId") userId: Int
    ) : Response<Unit>
}