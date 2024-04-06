package com.appdistaitec.classday.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object apiInstance {
    private val base_url="http://hermes.aitec.edu.ec:3000/"
    //private val base_url="https://debae353-b3da-461a-a721-439714e6ca02.mock.pstmn.io"
    private val apiinstance:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            //.client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        apiinstance.create(ApiService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer your_token_here") // Reemplaza "your_token_here" con tu token de autenticación
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        return builder.build()
    }

}