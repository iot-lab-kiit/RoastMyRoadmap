package in.iot.lab.roastmychoice.data.model

data class CreateLevelChoiceResponse(
    val id: Int,
    val level: Level,
    val selected: Int,
    val user: User
)