package com.appdistaitec.classday.service.hermes

import com.appdistaitec.classday.model.InfoBody
import com.appdistaitec.classday.model.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("app")
    suspend fun getInfoApiHermes(
        @Header("Authorization") token:String,
        @Body infobody: InfoBody
    ): Response<ResponseBody>

}