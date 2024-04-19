package com.appdistaitec.mainscreen.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appdistaitec.classday.data.InfoRepositoryDistAitec
import com.appdistaitec.datasource.Carreras
import com.appdistaitec.datasource.Ciclos
import com.appdistaitec.datasource.Cursos
import com.appdistaitec.datasource.Distributivo
import kotlinx.coroutines.launch

//@HiltViewModel
class DistributivoViewModel /*@Inject constructor(
    private val infoRepositoryDistAitec: InfoRepositoryDistAitec
)*/:ViewModel() {
   private val infoRepositoryDistAitec= InfoRepositoryDistAitec()

    var infoCarreras= mutableStateListOf<Carreras>()
    var infoCursos= mutableStateListOf<Cursos>()
    var infoCiclos= mutableStateListOf<Ciclos>()

    var infoDistributivo by mutableStateOf<Distributivo?>(null)

//    var idCarrera:Int?=null
//    var idCurso:Int?=null
//    var idCiclo:Int?=null

    suspend fun fetchData() {
        try {
            viewModelScope.launch {
                //Carreras
                val responseCarreras=infoRepositoryDistAitec.getCarreras().data
                val carreras=responseCarreras.map { result->
                    Carreras(
                        result.id,
                        result.name
                    )
                }
                infoCarreras.addAll(carreras)

            //Cursos
                val responseCursos=infoRepositoryDistAitec.getCursos().data
                val cursos=responseCursos.map { result->
                    Cursos(
                        result.id,
                        result.curso,
                        result.idCarrera
                    )
                }
                infoCursos.addAll(cursos)

                //Ciclos
                val responseCiclos=infoRepositoryDistAitec.getCiclos().data
                val ciclos=responseCiclos.map {result->
                    Ciclos(
                        result.id,
                        result.ciclo
                    )
                }
                infoCiclos.addAll(ciclos)


            }
        }catch (e: Exception){
            Log.d("FetchDistributivo: ",e.toString())
        }
    }

   fun fetchDataDist(carrera:String,nivel:Int,paralelo:String,ciclo:String,jornada:Int) {
       viewModelScope.launch {
            try {
                val idCarrera= obtenerIdCarreraPorNombre(carrera,infoCarreras)
                val idCurso=obtenerIdCurso(carrera,nivel,paralelo,infoCursos)
                val idCiclo=obtenerIdCiclo(ciclo,infoCiclos)
                val response=infoRepositoryDistAitec.getDistributivo(idCarrera,idCurso,idCiclo,jornada)

                infoDistributivo= Distributivo(
                    response?.asignatura ?: "",
                    response?.profesor ?: "",
                    response?.aula ?: "",
                    response?.latitud ?: "",
                    response?.longitud ?:""
                )

            }catch (e: Exception){
                Log.d("FetchDistributivo: ",e.toString())
            }
        }

    }

    fun obtenerIdCarreraPorNombre(nombreCarrera: String, carreras: List<Carreras>): Int? {
        val carreraEncontrada = carreras.find { it.name.trim() == nombreCarrera.trim() }
        return carreraEncontrada?.id
    }

    fun obtenerIdCiclo(nameCiclo: String, ciclos: List<Ciclos>): Int? {
        val carreraEncontrada = ciclos.find { it.ciclo.trim() == nameCiclo.trim() }
        return carreraEncontrada?.id
    }

    fun obtenerIdCurso(carrera: String,curso: Int, paralelo: String, cursos: List<Cursos>): Int? {
        // Generar el formato deseado con número romano y paralelo
        val nivelRomano = numeroARomano(curso)
        val cadenaBuscada = "$nivelRomano \"$paralelo\""+" ("+carrera+")"

        // Buscar el ID que coincida con la cadena generada
        val cursoEncontrado = cursos.find { it.curso == cadenaBuscada }

        return cursoEncontrado?.id
    }

    // Función para convertir un número entero a número romano
    fun numeroARomano(numero: Int): String {
        val romanos = listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X")
        return if (numero in 1..10) romanos[numero - 1] else ""
    }

}