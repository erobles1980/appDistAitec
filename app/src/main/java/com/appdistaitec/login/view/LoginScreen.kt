package com.appdistaitec.login.view

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appdistaitec.Navigation.Screens
import com.appdistaitec.R
import com.appdistaitec.login.service.AuthRes
import com.appdistaitec.login.service.AuthUiClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(
   auth: AuthUiClient,
   navigation: NavController
){
    val context = LocalContext.current
    val scope= rememberCoroutineScope()

    val googleSignInLauncher= rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (val account =
            auth.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))) {
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account?.data?.idToken, null)
                scope.launch {
                    val fireUser = auth.signInWithGoogleCredential(credential)
                    if (fireUser != null) {
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                        navigation.navigate(Screens.MainScreen.screen) {
                            popUpTo(Screens.LoginScreen.screen) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            is AuthRes.Error -> {
                Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Login(Modifier.align(Alignment.Center),auth,googleSignInLauncher)
    }
}


@Composable
fun Login(
    modifier: Modifier,
    auth: AuthUiClient,
    googleSignInLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {
    Column (modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        StartTag(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(12.dp))
        GoogleButton(auth,googleSignInLauncher)
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
    auth: AuthUiClient,
    googleSignInLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) {

    Button(
        onClick = {
            auth.signInWithGoogle(googleSignInLauncher)
        },
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