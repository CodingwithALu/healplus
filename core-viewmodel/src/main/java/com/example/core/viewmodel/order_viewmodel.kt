package com.example.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.Oder.Order
import com.example.core.network.retrofitclients.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderViewModel1: ViewModel (){
    private val _orders = MutableLiveData<MutableList<Order>>()
    val orders: LiveData<MutableList<Order>> = _orders
    private var isLoading by mutableStateOf(false)

    // load order
    fun loadOrder(){
        RetrofitClient.instance.getOder().enqueue(object : Callback<List<Order>>{
            override fun onResponse(
                call: Call<List<Order>?>,
                response: Response<List<Order>?>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(
                call: Call<List<Order>?>,
                t: Throwable
            ) {
                TODO("Not yet implemented")
            }

        })
    }
}