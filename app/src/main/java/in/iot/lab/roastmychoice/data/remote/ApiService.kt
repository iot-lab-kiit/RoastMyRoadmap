package `in`.iot.lab.roastmychoice.data.remote

import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserBody
import `in`.iot.lab.roastmychoice.data.model.CreateUserResponse
import `in`.iot.lab.roastmychoice.data.model.CreateLevelChoiceResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
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

}