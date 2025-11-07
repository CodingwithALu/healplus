package com.example.core.model.revenue

data class RevenueData(
    val order_year: Int? = null,
    val order_month: Int? = null,
    val order_day: Int? = null,
    val total_revenue: Float
){
    companion object{
        fun empty() = RevenueData(
            order_year = 0,
            order_month = 0,
            order_day = 0,
            total_revenue = 0.0F
        )
    }
}
data class DetailedOrder(
     val id: Int,
     val datetime: String,
    val sumMoney: Int
){
    companion object {
        fun empty() = DetailedOrder(
                id = 0, datetime = "", sumMoney = 0
            )
    }
}
data class RevenueResponse(
     val revenue: List<RevenueData>,
     val detaily_orders: List<DetailedOrder>
){
    companion object {
        fun empty() = RevenueResponse(
            revenue = emptyList(),
            detaily_orders = emptyList()
        )
    }
}
