package `in`.iot.lab.roastmychoice.data.model

data class CreateLevelChoiceBody(
    val levelId: Int,
    val selected: Int,
    val userId: Int
)