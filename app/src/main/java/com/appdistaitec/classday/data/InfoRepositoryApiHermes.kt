package com.appdistaitec.classday.data

import com.appdistaitec.classday.model.InfoBody
import com.appdistaitec.classday.model.ResponseBody
import com.appdistaitec.classday.service.hermes.ApiService
import retrofit2.Response
import javax.inject.Inject


class InfoRepositoryApiHermes @Inject constructor(private val apiService: ApiService,){
    //private val  apiService=apiInstance.apiService

    private val token:String="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkJlTUxjWFQiLCJ1c2VybmFtZSI6Imhlcm1lc0FwaSIsImlhdCI6MTY4NTcyMDk1NX0.FLIeZ6gp7DzR9xa6aX7Pd3TPcIUxqamT-FPL6ynOZgo"

    suspend fun getInfo(cedula: String, periodo: String, semestre: String): Response<ResponseBody> {
        return apiService.getInfoApiHermes(token, InfoBody(cedula,periodo,semestre) )
    }


}