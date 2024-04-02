package com.appdistaitec

sealed class Screens (val screen:String) {
    data object Login: Screens("login")
    data object MainScreen: Screens("main")
}