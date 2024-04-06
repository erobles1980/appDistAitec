package com.appdistaitec.classday.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.classday.data.infoRepositoryApiHermes
import com.appdistaitec.classday.model.InfoEstudiantes
import kotlinx.coroutines.launch

class ClassDayViewModel:ViewModel(){
    private val infoRepository=infoRepositoryApiHermes()

    var infoestudiantes by mutableStateOf<InfoEstudiantes?>(null)

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

}






