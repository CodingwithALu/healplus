package com.example.core.model.api

data class ApiResponse<T>(
    val success: Boolean,
    val message: String?,
    val result: T?
) {
    companion object {
        fun <T> empty() = ApiResponse<T>(
            success = false,
            message = "",
            result = null
        )
    }
}
data class ApiRequest(
    val success: Boolean,
    val message: String
) {
    companion object {
        fun empty() = ApiRequest(
            success = false,
            message = ""
        )
    }
}
