package com.appdistaitec.classday.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object apiInstance {

    private val base_url="https://hermes.aitec.edu.ec:3001/"
    //private val base_url="https://debae353-b3da-461a-a721-439714e6ca02.mock.pstmn.io"

    @Provides
    @Singleton
    fun provideRetrofit():Retrofit=Retrofit.Builder()
                                .baseUrl(base_url)
                                //.client(createOkHttpClient())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}