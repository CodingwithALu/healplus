package com.example.core.repository

import com.example.core.model.Oder.Order
import com.example.core.model.api.ApiResponse
import com.example.core.model.products.ProductsModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class OrderRepository(
    private val api: ApiService = RetrofitClient.instance
){
    init {

    }
    // add Order
    suspend fun createOrder(order: Order, dateTime: LocalDate, items: List<ProductsModel>): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            val gson = Gson()
            result = api.addOder(
                order.name,
                order.phone,
                order.email,
                order.userId,
                order.address,
                dateTime,
                order.note.toString(),
                order.quantity,
                order.sumMoney,
                order.status,
                gson.toJson(items)
            )
        }
        return result
    }
}