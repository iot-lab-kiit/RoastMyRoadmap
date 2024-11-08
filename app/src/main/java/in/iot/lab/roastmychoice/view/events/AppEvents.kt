package `in`.iot.lab.roastmychoice.view.events

sealed class AppEvents {
    data class CreateUser(val name: String, val rollNo: String, val domainId: Int) : AppEvents()
    data object ResetCreateState : AppEvents()
    data object FetchDomainData : AppEvents()
    data class CreateLevelChoice(val levelId: Int, val selected: Int) : AppEvents()
    data object ResetChoiceLevel : AppEvents()
    data object FetchRoast : AppEvents()
    data object DeleteUser : AppEvents()
    data object ResetDeleteState : AppEvents()
}