package com.example.core.network.retrofitclients

import com.example.core.network.apis.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConstants {
    const val BASE_URL = "http://localhost:8080/"
}
object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val instance: ApiService = retrofit.create(ApiService::class.java)
}