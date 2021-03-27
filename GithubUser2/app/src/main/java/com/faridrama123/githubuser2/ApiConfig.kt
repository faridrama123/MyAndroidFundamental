package com.faridrama123.githubuser2

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body

class ApiConfig {
    companion object{
        fun getApiService (): ApiService {
            val logger =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client =
                OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
            val retrofit =
                Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}