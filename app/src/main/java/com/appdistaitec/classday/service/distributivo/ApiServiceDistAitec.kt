package com.appdistaitec.classday.service.distributivo

import com.appdistaitec.classday.data.CarrerasResponse
import com.appdistaitec.classday.data.CiclosResponse
import com.appdistaitec.classday.data.CursosResponse
import com.appdistaitec.classday.data.DistributivoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceDistAitec {
    @GET("ciclos")
    suspend fun getCiclos():CiclosResponse

    @GET("carreras")
    suspend fun getCarreras():CarrerasResponse

    @GET("cursos")
    suspend fun getCursos():CursosResponse

    @GET("distributivos/{idCarrera}/{idCurso}/{idCiclo}/{idJornada}")
    suspend fun getDistributivo(
        @Path("idCarrera") idCarrera: Int?,
        @Path("idCurso") idCurso: Int?,
        @Path("idCiclo") idCiclo: Int?,
        @Path("idJornada") idJornada: Int
    ):DistributivoResponse

}