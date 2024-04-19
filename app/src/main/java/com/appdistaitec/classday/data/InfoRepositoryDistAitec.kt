package com.appdistaitec.classday.data

import com.appdistaitec.classday.service.distributivo.ApiInstanceDistAitec

class InfoRepositoryDistAitec {

    private val apiServiceDistAitec=ApiInstanceDistAitec.apiServiceDistAitec

    suspend fun getCiclos():CiclosResponse{
        return apiServiceDistAitec.getCiclos()
    }

    suspend fun getCursos():CursosResponse{
        return apiServiceDistAitec.getCursos()
    }

    suspend fun getCarreras():CarrerasResponse{
        return apiServiceDistAitec.getCarreras()
    }

    suspend fun getDistributivo(idCarrera: Int?, idCurso: Int?, idCiclo: Int?, idJornada: Int): DistributivoResponse {
        return apiServiceDistAitec.getDistributivo(idCarrera, idCurso, idCiclo, idJornada)
    }
}