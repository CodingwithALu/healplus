package com.example.core.repository

import com.example.core.model.Oder.OrderModel
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
    // fetch order
    suspend fun fetchOrder(): List<OrderModel>{
        return withContext(Dispatchers.IO){
            api.getOder()
        }
    }
    // fetch from user
    suspend fun fetchOrderFromUser(idUser: String): List<OrderModel>{
        var result = emptyList<OrderModel>()
        withContext(Dispatchers.IO){
            result = api.getOrderByUser(idUser)
        }
        return result
    }
    // fetch order by status
    suspend fun fetchOrderByStatus(status: String): List<OrderModel>{
        var result = emptyList<OrderModel>()
        withContext(Dispatchers.IO){
            result = api.getOrderStatus(status)
        }
        return result
    }
    // fetch order by status for user
    suspend fun fetchOrderByStatusFromUser(idUser: String, status: String): List<OrderModel>{
        var result = emptyList<OrderModel>()
        withContext(Dispatchers.IO){
            result = api.getOrderByStatusByUser(idUser, status)
        }
        return result
    }
    // add OrderModel
    suspend fun createOrder(orderModel: OrderModel, dateTime: LocalDate, items: List<ProductsModel>): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            val gson = Gson()
            result = api.addOder(
                orderModel.name,
                orderModel.phone,
                orderModel.email,
                orderModel.userId,
                orderModel.address,
                dateTime,
                orderModel.note.toString(),
                orderModel.quantity,
                orderModel.sumMoney,
                orderModel.status,
                gson.toJson(items)
            )
        }
        return result
    }
    // update status for order
    suspend fun updateStatusForOrder(orderId: Int, status: String): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.updateOrderStatus(orderId, status)
        }
        return result
    }
}