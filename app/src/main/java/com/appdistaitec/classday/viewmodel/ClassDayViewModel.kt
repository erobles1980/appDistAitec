package com.appdistaitec.classday.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.classday.data.infoRepositoryApiHermes
import com.appdistaitec.classday.model.InfoEstudiantes
import kotlinx.coroutines.launch

class ClassDayViewModel:ViewModel(){
    private val infoRepository=infoRepositoryApiHermes()

    var infoestudiantes= mutableStateListOf<InfoEstudiantes>()

    suspend fun fetchData(cedula: String) {
       viewModelScope.launch {
           try {
               val response = infoRepository.getInfo(cedula).body()

               infoestudiantes.add(
                   InfoEstudiantes(
                       response?.carrera ?: "",
                       response?.periodo ?: "",
                       response?.semestre ?: "",
                       response?.curso ?: "",
                       response?.nivel ?: 0,
                       response?.paralelo ?:""
                   )
               )
           } catch (e: Exception) {
               Log.d("FetchRetrofit: ",e.toString())
           }
       }
    }

}






