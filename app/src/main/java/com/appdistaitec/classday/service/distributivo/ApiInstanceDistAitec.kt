package com.appdistaitec.classday.service.distributivo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
object ApiInstanceDistAitec {

    private val base_url="https://distributivo.aitec.edu.ec/api/v1/"

//    @Provides
//    @Singleton
    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
    val apiServiceDistAitec: ApiServiceDistAitec by lazy{
        retrofit.create(ApiServiceDistAitec::class.java)
    }

}