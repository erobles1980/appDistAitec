package com.appdistaitec.login.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.login.model.SignInResult
import com.appdistaitec.login.model.SignInState
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenViewModel:ViewModel() {
    private val _state= MutableStateFlow(SignInState())
    val state=_state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
//    private val auth:FirebaseAuth =Firebase.auth
//    private val _loading=MutableLiveData(false)
//
//    fun signInWithGoogleCredential(credential: AuthCredential,home: () -> Unit)=viewModelScope.launch{
//        try {
//            auth.signInWithCredential(credential)
//                .addOnCompleteListener{task->
//                    if (task.isSuccessful){
//                        Log.d("appDistAitec","Logueado con Google")
//                    }
//
//                }
//                .addOnFailureListener{
//                    Log.d("appDistAitec","Fallo Logueado con Google")
//                }
//        }
//        catch (ex:Exception){
//            Log.d("appDistAitec","Excepcion al Logueado con Google"+
//                    "${ex.localizedMessage}")
//        }
//    }
//    fun sigInWithEmailAndPassword(email:String, password:String,home: ()-> Unit) = viewModelScope.launch{
//        try {
//            auth.signInWithEmailAndPassword(email,password)
//                .addOnCompleteListener{task->
//                    if (task.isSuccessful) {
//                        Log.d("appDistAitec","Ingreso Correcto")
//                    }else{
//                        Log.d("appDistAitec","Ingreso No Correcto")
//                    }
//                }
//        }
//        catch (ex:Exception){
//            Log.d("appDistAitec","Excepcion al Logueado"+
//                    "${ex.localizedMessage}")
//        }
//    }
}