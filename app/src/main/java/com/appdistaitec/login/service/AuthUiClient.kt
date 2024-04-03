package com.appdistaitec.login.service

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.appdistaitec.R
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

sealed class AuthRes<out T>{
    data class Success<T>(val  data: T): AuthRes<T>()
    data class Error(val errorMessage:String):AuthRes<Nothing>()
}

class AuthUiClient (
    private val context: Context
){
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val signInClient = Identity.getSignInClient(context)

    fun signOut() {
        auth.signOut()
        signInClient.signOut()
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }

    private val googleSignInClient:GoogleSignInClient by lazy {
        val gso = Builder(DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context,gso)
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount>? {
        return try {
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        } catch (e: ApiException) {
            AuthRes.Error(e.message ?: "Google sign-in failed.")
        }
    }

    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser>? {
        return try {
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sign in with Google failed.")
        } catch (e: Exception) {
            AuthRes.Error(e.message ?: "Sign in with Google failed.")
        }
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>) {
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }
//    suspend fun signIn(): IntentSender? {
//        val result = try {
//            oneTapClient.beginSignIn(
//                buildSignInRequest()
//            ).await()
//        } catch(e: Exception) {
//            e.printStackTrace()
//            if(e is CancellationException) throw e
//            null
//        }
//        return result?.pendingIntent?.intentSender
//    }

//    suspend fun signInWithIntent(intent: Intent): SignInResult {
//        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
//        val googleIdToken = credential.googleIdToken
//        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
//        return try {
//            val user = auth.signInWithCredential(googleCredentials).await().user
//            SignInResult(
//                data = user?.run {
//                                 UserData(
//                                     userId = uid,
//                                     userName = displayName,
//                                     profilePictureUrl = photoUrl?.toString()
//                                 )
//                },
//                errorMessage = null
//            )
//        } catch(e: Exception) {
//            e.printStackTrace()
//            if(e is CancellationException) throw e
//            SignInResult(
//                data = null,
//                errorMessage = e.message
//            )
//        }
//    }

//    suspend fun signOut() {
//        try {
//            oneTapClient.signOut().await()
//            auth.signOut()
//        } catch(e: Exception) {
//            e.printStackTrace()
//            if(e is CancellationException) throw e
//        }
//    }

//    fun getSignedInUser(): UserData? = auth.currentUser?.run {
//        UserData(
//            userId = uid,
//            userName = displayName,
//            profilePictureUrl = photoUrl?.toString()
//        )
//    }
//
//    private fun buildSignInRequest(): BeginSignInRequest {
//        return BeginSignInRequest.Builder()
//            .setGoogleIdTokenRequestOptions(
//                GoogleIdTokenRequestOptions.builder()
//                    .setSupported(true)
//                    .setFilterByAuthorizedAccounts(false)
//                    .setServerClientId(context.getString(R.string.web_client_id))
//                    .build()
//            )
//            .setAutoSelectEnabled(true)
//            .build()
//    }
}