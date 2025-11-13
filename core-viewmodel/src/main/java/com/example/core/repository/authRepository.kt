package com.example.core.repository

import com.example.core.model.api.ApiResponse
import com.example.core.model.users.UserModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val api: ApiService = RetrofitClient.instance
) {
    // signIn password and email
    suspend fun signInWithEmailPassword(email: String, password: String) {
        return withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).await()
        }
    }

    suspend fun verifyEmail(): Boolean {
        auth.currentUser?.reload()?.await()
        return withContext(Dispatchers.IO) {
            auth.currentUser?.isEmailVerified == true
        }
    }
    // verify email
    suspend fun sendEmailVerification() {
        return withContext(Dispatchers.IO) {
            val user = auth.currentUser
            if (user != null) {
                user.reload().await()
                if (!user.isEmailVerified) {
                    user.sendEmailVerification().await()
                } else {
                    throw IllegalStateException("Người dùng đã được xác thực.")
                }
            } else {
                throw IllegalStateException("Người dùng chưa đăng nhập.")
            }
        }
    }
    suspend fun sendVerificationEmailIfNeeded() {
        return withContext(Dispatchers.IO) {
            val user = auth.currentUser
            if (user != null) {
                try {
                    user.reload().await()
                    if (!user.isEmailVerified) {
                        user.sendEmailVerification().await()
                    }
                } catch (e: Exception) {
                    throw e
                }
            } else {
                // nếu không có user hiện tại, không làm gì
            }
        }
    }

    // SignUp
    suspend fun createUser(name: String, email: String, password: String): ApiResponse {
        return withContext(Dispatchers.IO) {
            try {
                val creteAuth = auth.createUserWithEmailAndPassword(email, password).await()
                auth.currentUser?.reload()?.await()
                val userId = creteAuth.user?.uid ?: throw Exception("User ID is null")
                val userModel = UserModel(
                    id = userId,
                    name = name,
                    email = email,
                    password = password
                )
                val dbResult = createUserForDataBase(userModel)
                if (dbResult.success) {
                    dbResult
                } else {
                    auth.currentUser?.delete()?.await()
                    throw Exception("Failed to create user in database: ${dbResult.message}")
                }
            } catch (e: Exception) {
                ApiResponse(false, e.message ?: "Unknown error")
            }
        }
    }

    suspend fun createUserForDataBase(userModel: UserModel): ApiResponse {
        return withContext(Dispatchers.IO) {
            api.createUser(
                userModel.id,
                userModel.name,
                userModel.email,
                userModel.password
            )
        }
    }

    // fetch user data
    suspend fun fetchUserFromData(): UserModel {
        return if (auth.currentUser != null) {
            withContext(Dispatchers.IO) {
                api.fetchUserFromData(auth.currentUser?.uid.toString())
            }
        } else
            UserModel.empty()
    }

    // Logout
    fun signOut() {
        auth.signOut()
    }
    // fetch id
    fun fetchIdAuth(): String{
        var result = ""
        if(auth.currentUser != null){
            result =  auth.currentUser?.uid.toString()
        }
        return result
    }
}

sealed class EmailVerifyEvent {
    data class ShowErrorSnackBar(val title: String, val message: String) :
        EmailVerifyEvent()

    object RedirectToSuccessScreen : EmailVerifyEvent()
    object RedirectToVerifyScreen : EmailVerifyEvent()
}