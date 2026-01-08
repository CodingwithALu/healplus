package com.example.core.repository

import com.example.core.model.api.ApiRequest
import com.example.core.model.products.conten.ReviewItem
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ReviewRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    suspend fun createReview(review: ReviewItem): ApiRequest {
        var result = ApiRequest.empty()
        val reviewMap = review.toJsonMap().toMutableMap()
        withContext(Dispatchers.IO){
            result = api.createReview(reviewMap)
            delay(1000L)
        }
        return result
    }
    suspend fun updateReview(id: String): ApiRequest{
        var result = ApiRequest.empty()
        withContext(Dispatchers.IO){
            result = api.updateReview(id)
        }
        return result
    }
}