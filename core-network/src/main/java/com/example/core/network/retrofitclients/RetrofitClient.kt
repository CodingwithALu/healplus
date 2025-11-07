package com.example.core.network.retrofitclients

import com.example.core.network.apis.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConstants {
    // If backend runs on local machine and you use Android emulator, use 10.0.2.2
    const val BASE_URL = "http://10.169.179.44:3306/"
}
object RetrofitClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val instance: ApiService = retrofit.create(ApiService::class.java)
}