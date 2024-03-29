package com.appdistaitec.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.appdistaitec.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Login(Modifier.align(Alignment.Center))
    }
}


@Composable
fun Login(modifier: Modifier) {
    Column (modifier = modifier) {
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(16.dp))
        StartTag(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(12.dp))
        GoogleButton()
    }
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
fun GoogleButton() {
    Button(
        onClick = { /*TODO*/ },
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

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logoaitec),
        contentDescription = "Logo",
        modifier=modifier
    )
}
