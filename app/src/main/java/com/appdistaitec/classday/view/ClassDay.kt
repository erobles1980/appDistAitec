package com.appdistaitec.classday.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appdistaitec.R
import com.appdistaitec.classday.viewmodel.ClassDayViewModel
import com.appdistaitec.login.service.AuthUiClient


@Composable
fun ClassDay(auth: AuthUiClient,viewModel: ClassDayViewModel= hiltViewModel()){

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
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxSize()
){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if (!dominio.toString().equals("aitec.edu.ec")){
           Image(
                painter = painterResource(id = R.drawable.notfound),
                contentDescription ="Error",
                modifier = Modifier
                    .size(350.dp)
            )
            Text(
                text = "Lo sentimos usted no es estudiante del instituto",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .padding(16.dp)
            )

        }else{
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


}