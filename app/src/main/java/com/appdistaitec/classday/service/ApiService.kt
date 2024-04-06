package com.appdistaitec.classday.service

import com.appdistaitec.classday.model.CedulaBody
import com.appdistaitec.classday.model.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("app")
    suspend fun getInfoApiHermes(
        @Header("Authorization") token:String,
        @Body cedula: CedulaBody
    ): Response<ResponseBody>

}