package com.example.core.model.api

data class ApiResponse(
    val success: Boolean,
    val message: String
) {
    companion object {
        fun empty() = ApiResponse(
            success = false,
            message = ""
        )
    }
}
