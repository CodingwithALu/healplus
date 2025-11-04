package com.example.core.repository

import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient

class ProductRepository(
    private val api: ApiService = RetrofitClient.instance
){
    // add product
    suspend fun
}