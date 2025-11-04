package com.example.core.model.Oder

import com.example.core.model.products.ProductsModel

data class Order(
    val id: Int,
    val userId: String,
    val name: String,
    val email: String,
    val address: String,
    val phone: String,
    val quantity: Int,
    val datetime: String ?= null,
    val sumMoney: Float,
    val note: String ?= null,
    val status: String,
    val items: List<ProductsModel>
) {
    companion object{
        fun empty() = Order(
            id = 0,
            userId = "",
            name = "",
            email = "",
            address = "",
            phone = "",
            quantity = 0,
            datetime = "",
            sumMoney = 1.0f,
            note = "",
            status = "",
            items = listOf(ProductsModel.empty())
        )
    }

    // Optional: convert Order to a Map payload for easy upload
    fun toJsonMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "userId" to userId,
            "name" to name,
            "email" to email,
            "address" to address,
            "phone" to phone,
            "quantity" to quantity,
            "datetime" to (datetime ?: ""),
            "sumMoney" to sumMoney,
            "note" to (note ?: ""),
            "status" to status,
            "items" to items.map { it.toJsonMap() }
        )
    }
}