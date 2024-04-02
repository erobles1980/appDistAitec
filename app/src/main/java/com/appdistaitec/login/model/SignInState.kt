package com.appdistaitec.login.model

data class SignInState(
    val isSignInSuccessful: Boolean=false,
    val signInError: String?=null
)
