package com.appdistaitec.classday.data

import com.appdistaitec.classday.model.CedulaBody
import com.appdistaitec.classday.model.ResponseBody
import com.appdistaitec.classday.service.apiInstance
import retrofit2.Response


class infoRepositoryApiHermes{
    private val  apiService=apiInstance.apiService

    private val token:String="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IkJlTUxjWFQiLCJ1c2VybmFtZSI6Imhlcm1lc0FwaSIsImlhdCI6MTY4NTcyMDk1NX0.FLIeZ6gp7DzR9xa6aX7Pd3TPcIUxqamT-FPL6ynOZgo"

    suspend fun getInfo(cedula:String): Response<ResponseBody> {
//        val requestBody= RequestBody.create(
//            contentType = "application/json".toMediaTypeOrNull(),
//            "{\"cedula\":\"$cedula\"}"
//        )

        return apiService.getInfoApiHermes(token, CedulaBody(cedula) )
        //val requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"cedula\":\"0931424485\"}")
    }


}