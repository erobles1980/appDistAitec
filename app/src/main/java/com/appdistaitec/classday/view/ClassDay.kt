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
import com.appdistaitec.classday.viewmodel.ClassDayViewModel
import com.appdistaitec.login.service.AuthUiClient


@Composable
fun ClassDay(auth: AuthUiClient,viewModel: ClassDayViewModel){
    val user = auth.getCurrentUser()

    //val viewModel:ClassDayViewModel = viewModel()

    val infoEstudiante=viewModel.infoestudiantes

    val cedula= "0951076678"//user?.email?.let { it.substringBefore("@") }
    val periodo="2023-2024"
    val semestre="B"
    val dominio= user?.email?.let {  it.substringAfter("@") }

    LaunchedEffect(Unit) {
        //if (nickname != null) {
            viewModel.fetchData(cedula,periodo,semestre)
        //}
    }
Box(
    modifier = Modifier
        .fillMaxSize()
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (infoEstudiante!=null){
            Text(text = infoEstudiante.carrera)
        }else{
            Text(text = "No se tiene informacion")
        }

        Text(text = "Jormada de Clases")
        Text(text = cedula)
        Text(text = dominio.toString())
    }

}


}