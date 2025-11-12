package com.example.core.repository

import com.example.core.model.api.ApiResponse
import com.example.core.model.products.conten.ReviewItem
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ReviewRepository(
    private val api: ApiService = RetrofitClient.instance
) {
    suspend fun createReview(review: ReviewItem, idp: String): ApiResponse {
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.addReview(
                review.name,
                review.rating,
                review.comment,
                review.date,
                review.ulr!!,
                idp
            )
            delay(1000L)
        }
        return result
    }
    suspend fun updateReview(id: String): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.updateReview(id)
        }
        return result
    }
}