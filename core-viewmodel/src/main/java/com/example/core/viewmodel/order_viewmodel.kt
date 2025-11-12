package com.example.core.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Oder.OrderModel
import com.example.core.model.products.ProductsModel
import com.example.core.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    private val _orders = MutableLiveData<MutableList<OrderModel>>()
    val orders: LiveData<MutableList<OrderModel>> = _orders
    private val _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message
    private var isLoading by mutableStateOf(false)

    init {
        fetchOrder()
    }

    // load order
    fun fetchOrder() {
        viewModelScope.launch {
            withContext(NonCancellable){
                val result = orderRepository.fetchOrder()
                _orders.value = result as MutableList<OrderModel>?
            }
        }
    }

    // fetch order from user
    fun fetchOrderFromUser(idUser: String) {
        viewModelScope.launch {
            val result = async { orderRepository.fetchOrderFromUser(idUser) }
            _orders.value = result.await() as MutableList<OrderModel>
        }
    }

    fun fetchOrderByStatus(status: String) {
        viewModelScope.launch {
            val result = async {
                orderRepository.fetchOrderByStatus(status)
            }
            _orders.value = result.await() as MutableList<OrderModel>
        }
    }

    fun fetchOrderByStatusFromUser(idUser: String, status: String) {
        viewModelScope.launch {
            val result = async { orderRepository.fetchOrderByStatusFromUser(idUser, status) }
            _orders.value = result.await() as MutableList<OrderModel>
        }
    }

    // create OrderModel
    fun createOrder(orderModel: OrderModel, dateTime: LocalDate, items: List<ProductsModel>) {
        viewModelScope.launch {
            orderRepository.createOrder(
                orderModel,
                dateTime,
                items
            )
        }
    }

    // update status
    fun updateStatusForOrder(orderId: Int, status: String) {
        viewModelScope.launch {
            val result = async {
                orderRepository.updateStatusForOrder(orderId, status)
            }
            _message.value = result.await().message
        }
    }
}