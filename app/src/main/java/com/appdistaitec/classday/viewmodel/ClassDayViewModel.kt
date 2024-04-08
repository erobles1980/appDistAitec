package com.appdistaitec.classday.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.classday.data.InfoRepositoryApiHermes
import com.appdistaitec.classday.model.InfoEstudiantes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassDayViewModel @Inject constructor(
     private val infoRepository:InfoRepositoryApiHermes
):ViewModel(){

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
