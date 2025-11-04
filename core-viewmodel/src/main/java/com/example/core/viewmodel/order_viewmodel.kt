package com.example.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Oder.Order
import com.example.core.model.products.ProductsModel
import com.example.core.network.retrofitclients.RetrofitClient
import com.example.core.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
): ViewModel (){
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
    // create Order
    fun createOrder(order: Order, dateTime: LocalDate, items: List<ProductsModel>){
        viewModelScope.launch {
            orderRepository.createOrder(
                order,
                dateTime,
                items
            )
        }
    }
}