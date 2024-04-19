package com.appdistaitec.classday.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.classday.data.InfoRepositoryApiHermes
import com.appdistaitec.classday.data.InfoRepositoryDistAitec
import com.appdistaitec.classday.model.InfoEstudiantes
import com.appdistaitec.datasource.Ciclos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ClassDayViewModel @Inject constructor(
    private val infoRepository:InfoRepositoryApiHermes
):ViewModel(){

    private val infoRepositoryDistAitec=InfoRepositoryDistAitec()

    var infoestudiantes by mutableStateOf<InfoEstudiantes?>(null)

    var infoCiclos= mutableStateListOf<Ciclos>()

    suspend fun fetchData(cedula: String,periodo:String,semestre:String) {
       viewModelScope.launch {
           try {
               val response = infoRepository.getInfo(cedula,periodo,semestre).body()

               infoestudiantes= InfoEstudiantes(
                       response?.carrera ?: "",
                       response?.periodo ?: "",
                       response?.semestre ?: "",
                       response?.curso ?: "",
                       response?.nivel ?: 0,
                       response?.paralelo ?:""
                   )

           } catch (e: Exception) {
               Log.d("FetchRetrofit: ",e.toString())
           }
       }
    }



//    fun obtenerPeriodos():PeriodosResponse{
//        val periodos = mutableSetOf<String>()
//        // Obtener y guardar los rangos de años en la lista periodos
//        for (ciclo in infoCiclos) {
//            val periodo = obtenerRangoAnios(ciclo)
//            periodos.add(periodo)
//        }
//        return PeriodosResponse(periodos.toList())
//    }
//
//    private fun obtenerRangoAnios(ciclo: Ciclos): String {
//        // Divide la cadena por los paréntesis y toma la segunda parte
//        val anios = ciclo.ciclo.split("(")[1]
//        // Elimina los paréntesis y espacios adicionales al inicio y al final
//        return anios.replace(")", "").trim()
//    }


    fun obtenerSemestreYPeriodo(): Pair<String, String> {
        val calendar = Calendar.getInstance()
        val mesActual = calendar.get(Calendar.MONTH) + 1 // Sumamos 1 porque Calendar.MONTH devuelve valores de 0 a 11

        val semestre: String
        val periodo: String

        if (mesActual in 5..10) {
            semestre = "A"
            periodo = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.YEAR) + 1}"
        } else {
            semestre = "B"
            if (mesActual in 1..4) {
                periodo = "${calendar.get(Calendar.YEAR) - 1}-${calendar.get(Calendar.YEAR)}"
            } else {
                periodo = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.YEAR) + 1}"
            }
        }

        return Pair(semestre, periodo)
    }


}
