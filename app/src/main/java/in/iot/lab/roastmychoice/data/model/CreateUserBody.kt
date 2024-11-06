package `in`.iot.lab.roastmychoice.data.model

data class CreateUserBody(
    val domainId: Int,
    val name: String,
    val rollNo: String
)