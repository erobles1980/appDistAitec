package com.appdistaitec.classday.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.appdistaitec.classday.viewmodel.ClassDayViewModel
import com.appdistaitec.login.service.AuthUiClient


@Composable
fun ClassDay(auth: AuthUiClient){
    val user = auth.getCurrentUser()

    val viewModel:ClassDayViewModel = viewModel()

    val infoEstudiante=viewModel.infoestudiantes

    val nickname= "0931424485"//user?.email?.let { it.substringBefore("@") }
    val dominio= user?.email?.let {  it.substringAfter("@") }

    LaunchedEffect(Unit) {
        if (nickname != null) {
            viewModel.fetchData(nickname.toString())
        }
    }
Box(
    modifier = Modifier
        .fillMaxSize()
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (infoEstudiante.isNotEmpty()){
            Text(text = infoEstudiante[0].carrera.toString())
        }else{
            Text(text = "No se tiene informacion")
        }

        Text(text = "Jormada de Clases")
        Text(text = nickname.toString())
        Text(text = dominio.toString())
    }

}


}