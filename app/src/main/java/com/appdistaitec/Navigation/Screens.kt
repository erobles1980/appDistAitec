package com.appdistaitec.Navigation

sealed class Screens (val screen:String) {
    object LoginScreen: Screens("LoginScreen")
    object MainScreen: Screens("MainScreen")
    object ClassroomRoute: Screens("ClassroomRoute")
    data object ClassDay: Screens("ClassDay")
    object CareersClassDay: Screens("CareersClassDay")

}