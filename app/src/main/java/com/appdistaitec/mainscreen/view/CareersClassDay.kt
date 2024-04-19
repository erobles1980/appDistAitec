package com.appdistaitec.mainscreen.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun CareersClassDay(){
//variables de estado de icono
    var isExpanded by remember {mutableStateOf(false) }
    var isExpandedCurso by remember {mutableStateOf(false) }
    var isExpandedCiclo by remember {mutableStateOf(false) }
    var isExpandedJornada by remember {mutableStateOf(false) }

    //listas
    val list= listOf("carrera 1", "carrera2")
    val listCursos= listOf("I A","II A","III A")
    val listCiclo= listOf("1 ciclo","2 ciclo","3 ciclo")
    val listJornada= listOf("Mañana (08:00:00 - 12:00:00)","Tarde (13:00:00 - 17:00:00)")

    //informacion
    var seleccioneCarrera by remember {mutableStateOf("")}
    var seleccioneCurso by remember {mutableStateOf("")}
    var seleccioneCiclo by remember {mutableStateOf("")}
    var seleccioneJornada by remember {mutableStateOf("")}
    var idJornada by remember {mutableStateOf(0)}
    //otros
    var textFiledSize by remember { mutableStateOf(Size.Zero)}

    val icon = if(isExpanded){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }
    val iconCurso = if(isExpandedCurso){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }
    val iconCiclo = if(isExpandedCiclo){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }
    val iconJornada = if(isExpandedJornada){
        Icons.Default.KeyboardArrowUp
    }else{
        Icons.Default.KeyboardArrowDown
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
        ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Carrera
            OutlinedTextField(
                value = seleccioneCarrera,
                onValueChange = {seleccioneCarrera=it},
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Seleccione la Carrera") },
                trailingIcon = {
                    Icon(icon,"", Modifier.clickable { isExpanded = !isExpanded })
                }
            )

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded=false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                list.forEachIndexed{index,text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            seleccioneCarrera= list[index]
                            isExpanded=false
                        }
                    )
                }

            }

        //Cursos
            OutlinedTextField(
                value = seleccioneCurso,
                onValueChange = {seleccioneCurso=it},
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Seleccione el Curso") },
                trailingIcon = {
                    Icon(iconCurso,"", Modifier.clickable { isExpandedCurso = !isExpandedCurso })
                }
            )

            DropdownMenu(
                expanded = isExpandedCurso,
                onDismissRequest = { isExpandedCurso=false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                listCursos.forEachIndexed{index,text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            seleccioneCurso= list[index]
                            isExpandedCurso=false
                        }
                    )
                }

            }

        //Ciclo
            OutlinedTextField(
                value = seleccioneCiclo,
                onValueChange = {seleccioneCiclo=it},
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Seleccione el Ciclo") },
                trailingIcon = {
                    Icon(iconCiclo,"", Modifier.clickable { isExpandedCiclo = !isExpandedCiclo })
                }
            )

            DropdownMenu(
                expanded = isExpandedCiclo,
                onDismissRequest = { isExpandedCiclo=false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                listCiclo.forEachIndexed{index,text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            seleccioneCiclo= list[index]
                            isExpandedCiclo=false
                        }
                    )
                }

            }

            //Jornada

            OutlinedTextField(
                value = seleccioneJornada,
                onValueChange = {seleccioneJornada=it},
                modifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Seleccione la Jornada") },
                trailingIcon = {
                    Icon(iconCiclo,"", Modifier.clickable { isExpandedJornada = !isExpandedJornada })
                }
            )

            DropdownMenu(
                expanded = isExpandedJornada,
                onDismissRequest = { isExpandedJornada=false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textFiledSize.width.toDp()})
            ) {
                listJornada.forEachIndexed{index,text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            seleccioneJornada= list[index]
                            idJornada=index+1
                            isExpandedJornada=false
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

        }//termina column


    }//termina box




}