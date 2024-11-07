package `in`.iot.lab.roastmychoice.data.model

data class GetDomainLevelsResponse(
    val id: Int,
    val levels: List<Level>,
    val name: String
)