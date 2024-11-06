package in.iot.lab.roastmychoice.data.model

data class Level(
    val id: Int,
    val levelNo: Int,
    val options: List<String>,
    val question: String
)