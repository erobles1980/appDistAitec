package com.appdistaitec.login.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdistaitec.R
import com.appdistaitec.login.model.SignInState
import com.appdistaitec.login.viewmodel.LoginScreenViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(
    state: SignInState,
    onSignInClick: () -> Unit

    //viewModel: LoginScreenViewModel=androidx.lifecycle.viewmodel.compose.viewModel()
){
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts
//            .StartActivityForResult()
//    ) {
//        val task= GoogleSignIn.getSignedInAccountFromIntent(it.data)
//        try {
//            val account=task.getResult(ApiException::class.java)
//            val credential=GoogleAuthProvider.getCredential(account.idToken,null)
//      //      viewModel.signInWithGoogleCredential(credential){
//                //
//            //}
//        }
//        catch (e: Exception){
//
//        }
//    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Login(Modifier.align(Alignment.Center),onSignInClick)
    }
}


@Composable
fun Login(
    modifier: Modifier,
    onSignInClick: () -> Unit
    ) {
    Column (modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        StartTag(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(12.dp))
        GoogleButton(onSignInClick)
    }
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logoaitec),
        contentDescription = "Logo",
        modifier=modifier
    )
}

@Composable
fun StartTag(modifier: Modifier) {
    Text(
        text = "Distributivo de aula",
        fontFamily = FontFamily.SansSerif,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        modifier=modifier
    )
}

@Composable
fun GoogleButton(
    onSignInClick: () -> Unit
) {

    Button(
        onClick = onSignInClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFF0B4F9E))
    ) {
        Image(
            painter = painterResource(id = R.drawable.iconogoogle),
            contentDescription = "Icono de Google",
            modifier = Modifier.size(36.dp)
        )
        Spacer(modifier = Modifier.size(10.dp) )
        Text(
            text = "Iniciar Sesión",
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}