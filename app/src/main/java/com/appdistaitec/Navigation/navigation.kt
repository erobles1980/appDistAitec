package com.appdistaitec.Navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.appdistaitec.login.service.AuthUiClient
import com.appdistaitec.login.view.LoginScreen
import com.appdistaitec.mainscreen.view.MainScreen
import com.google.firebase.auth.FirebaseUser


@Composable
fun Navigation(
    context: Context,
    navController: NavHostController = rememberNavController()
){
    val authManager: AuthUiClient = AuthUiClient(context)

    val user: FirebaseUser? = authManager.getCurrentUser()

    NavHost(
        navController = navController,
        startDestination = if(user == null) Screens.LoginScreen.screen else Screens.MainScreen.screen
    ) {
        composable(Screens.LoginScreen.screen) {
            LoginScreen(
                auth = authManager,
                navigation = navController
            )
        }
        composable(Screens.MainScreen.screen) {
            MainScreen(
                auth = authManager,
                navigation = navController)
        }
    }

}