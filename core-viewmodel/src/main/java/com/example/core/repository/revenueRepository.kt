package com.example.core.repository

import com.example.core.model.revenue.RevenueResponse
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
class RevenueRepository(
    private val api: ApiService = RetrofitClient.instance
){
    // revenueYear
    suspend fun revenueYear(year: Int): RevenueResponse {
        var result = RevenueResponse.empty()
        withContext(Dispatchers.IO){
            result = api.revenueYear(year)
        }
        return result
    }
    // revenue months
    suspend fun revenueMonth(month: Int, year: Int): RevenueResponse{
        var result = RevenueResponse.empty()
        withContext(Dispatchers.IO){
            result = api.revenueMonth(month, year)
        }
        return result
    }
    // revenue week
    suspend fun revenueWeek(startDate: LocalDate): RevenueResponse{
        val startDateString = startDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
        var result = RevenueResponse.empty()
        withContext(Dispatchers.IO){
            result = api.revenueWeek(startDateString)
        }
        return result
    }
}