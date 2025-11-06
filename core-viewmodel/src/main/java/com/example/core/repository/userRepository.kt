package com.example.core.repository

import com.example.core.model.api.ApiResponse
import com.example.core.model.users.UserModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository (
    private val api: ApiService = RetrofitClient.instance
){
    suspend fun createUser(user: UserModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.addUser(
                user.name,
                user.email,
                user.password,
                user.url,
                user.role!!
            )
        }
        return result
    }
    // updateIdAuth
    suspend fun updateIdAuth(email: String, idAuth: String): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.upDateIdAuth(email, idAuth)
        }
        return result
    }
    // updateUser
    suspend fun updateUser(user: UserModel): ApiResponse{
        var result = ApiResponse.empty()
        withContext(Dispatchers.IO){
            result = api.upDateUser(
                user.name,
                user.email,
                user.gender,
                "0123456789",
                user.url,
                user.dateBirth,
                user.idUser
            )
        }
        return result
    }
}