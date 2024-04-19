package com.appdistaitec.classday.service.hermes

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


    private val base_url_H="https://hermes.aitec.edu.ec:3001/"
    //private val base_url_H="https://747c8df5-118f-44dc-956e-c4d418820713.mock.pstmn.io"

    @Provides
    @Singleton
    fun provideRetrofitH():Retrofit=Retrofit.Builder()
                                .baseUrl(base_url_H)
                                //.client(createOkHttpClient())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()

    @Provides
    @Singleton
    fun provideApiServiceH(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}