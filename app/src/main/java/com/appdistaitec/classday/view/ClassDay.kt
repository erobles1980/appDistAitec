@file:OptIn(ExperimentalMaterial3Api::class)

package com.appdistaitec.classday.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.appdistaitec.R
import com.appdistaitec.classday.viewmodel.ClassDayViewModel
import com.appdistaitec.login.service.AuthUiClient
import com.appdistaitec.mainscreen.viewmodel.DistributivoViewModel




@Composable
fun ClassDay(
    auth: AuthUiClient,
    viewModel: ClassDayViewModel = hiltViewModel(),
){
//MENU
    var viewModelDist: DistributivoViewModel = DistributivoViewModel()
    var expandedJornada by remember { mutableStateOf(false) }
    val listJornada= listOf("Mañana (08:00:00 - 12:00:00)","Tarde (13:00:00 - 17:00:00)")
    var selectedJornada by remember {mutableStateOf("")}
    var textFiledSize by remember { mutableStateOf(Size.Zero)}
    val iconJornada = if(expandedJornada){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }

//Otros


    val user = auth.getCurrentUser()
    var ciclo by remember {
        mutableStateOf("")
    }
    var jornada by remember {
        mutableStateOf(0)
    }
    //val viewModel:ClassDayViewModel = viewModel()

    val (semestre, periodo) = viewModel.obtenerSemestreYPeriodo()


    val infoEstudiante=viewModel.infoestudiantes
    val infoDistributivo=viewModelDist.infoDistributivo

    val cedula= user?.email?.let { it.substringBefore("@") }//"0951076678"
    //val periodo="2023-2024"
    //val semestre="B"
    val dominio= user?.email?.let {  it.substringAfter("@") }
    //val dominio="aitec.edu.ec"

    LaunchedEffect(Unit) {
        //if (nickname != null) {
            viewModel.fetchData(cedula.toString(),periodo,semestre)
            //viewModelDist.fetchData()
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
                Text(
                    text = infoEstudiante.carrera,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = periodo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Semestre ${semestre}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                dropDownMenu{valor->
                    ciclo=valor
                }


                OutlinedTextField(
                    value = selectedJornada,
                    onValueChange = {selectedJornada=it},
                    modifier = Modifier
                        //.fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFiledSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Seleccione la Jornada")},
                    trailingIcon = {
                        Icon(iconJornada,"",Modifier.clickable { expandedJornada = !expandedJornada })
                    }
                )

                DropdownMenu(
                    expanded = expandedJornada,
                    onDismissRequest = { expandedJornada=false },
                    modifier = Modifier
//                        .fillMaxWidth()
                        .width(with(LocalDensity.current){textFiledSize.width.toDp()})
                ) {
                    listJornada.forEachIndexed{index,text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = {
                                selectedJornada= listJornada[index]
                                jornada=index
                                expandedJornada=false
                            }
                        )
                    }

                }
                
                Button(
                    onClick = {
//                        if(ciclo!=null && !jornada.equals(0) ) {
//                            val cicloCadena = ciclo + " - Semestre " + semestre + " (" + periodo + ")"
//                                viewModelDist.fetchDataDist(
//                                    infoEstudiante.carrera,
//                                    infoEstudiante.nivel,
//                                    infoEstudiante.paralelo,
//                                    cicloCadena,
//                                    jornada + 1
//                                )
//                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF0B4F9E))
                    ) {
                    Text(
                        text = "Buscar",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }

//                val infoDistAitec = viewModelDist.infoDistributivo
//                if (infoDistAitec != null) {
//                    Text(
//                        text = infoDistAitec.asignatura,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Text(
//                        text = infoDistAitec.docente,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                    Text(
//                        text = infoDistAitec.aula,
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                } else {
//                    Text(
//                        text = "Aún no se tiene información de la materia",
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight.SemiBold
//                    )
//                }

            }else{
                Text(
                    text = "No se tiene informacion, es posible que el servidor este apagado",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )

            }

        }
    }

}
}

@Composable
fun dropDownMenu(onValueSelected: (String) -> Unit) {
    var expandedCiclo by remember { mutableStateOf(false) }
    val listCiclo= listOf("1 ciclo","2 ciclo","3 ciclo")
    var selectedCiclo by remember {mutableStateOf("")}

    var textFiledSize by remember { mutableStateOf(Size.Zero)}

    val icon = if(expandedCiclo){Icons.Default.KeyboardArrowUp}else{Icons.Default.KeyboardArrowDown}

    Column (modifier = Modifier.padding(12.dp)){
        OutlinedTextField(
            value = selectedCiclo,
            onValueChange = {selectedCiclo=it},
            modifier = Modifier
//                .fillMaxWidth()
            /*.onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                }*/,
            label = { Text(text = "Seleccione el ciclo")},
            trailingIcon = {
                Icon(icon,"",Modifier.clickable { expandedCiclo = !expandedCiclo })
            }
        )

        DropdownMenu(
            expanded = expandedCiclo,
            onDismissRequest = { expandedCiclo=false },
            modifier = Modifier
                .fillMaxWidth()
                .width(with(LocalDensity.current){textFiledSize.width.toDp()})
        ) {
            listCiclo.forEachIndexed{index,text ->
                DropdownMenuItem(
                    text = { Text(text = text) },
                    onClick = {
                        selectedCiclo=listCiclo[index]
                        expandedCiclo=false
                    }
                )
            }

        }
    }
    onValueSelected(selectedCiclo)
}

