package com.example.core.repository

import com.example.core.model.api.ApiResponse
import com.example.core.model.users.UserModel
import com.example.core.network.apis.ApiService
import com.example.core.network.retrofitclients.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val api: ApiService = RetrofitClient.instance
) {
    private var _emailVerify = MutableStateFlow<EmailVerifyEvent?>(null)
    val emailVerify: StateFlow<EmailVerifyEvent?> = _emailVerify
    private val user = auth.currentUser
    fun screenRedirect() {
        if (user != null) {
            if (user.isEmailVerified) {
                _emailVerify.value = EmailVerifyEvent.RedirectToUserEmpty
            } else {
                _emailVerify.value = EmailVerifyEvent.RedirectToSuccessScreen
            }
        } else {
            _emailVerify.value = EmailVerifyEvent.ShowErrorSnackBar(
                title = "No Account",
                message = "Create Account. Please!"
            )
        }
    }
    // signIn password and email
    suspend fun signInWithEmailPassword(email: String, password: String) {
        return withContext(Dispatchers.IO) {
            auth.signInWithEmailAndPassword(email, password).await()
        }
    }
    // verify email
    suspend fun sendEmailVerification(){
        return withContext(Dispatchers.IO){
            auth.currentUser?.sendEmailVerification()
        }
    }
    // SignUp
    suspend fun createUser(name: String, email: String, password: String): ApiResponse{
        return withContext(Dispatchers.IO) {
            try {
                val creteAuth = auth.createUserWithEmailAndPassword(email, password).await()
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
                    throw Exception("Failed to create user in database: ${dbResult.message}")
                }
            } catch (e: Exception) {
                ApiResponse(false, e.message ?: "Unknown error")
            }
        }
    }
    suspend fun createUserForDataBase(userModel: UserModel): ApiResponse{
        return withContext(Dispatchers.IO) {
            api.createUser(
                userModel.id,
                userModel.name,
                userModel.email,
                userModel.password
            )
        }
    }
    // Logout
    fun signOut() {
        auth.signOut()
    }
}
sealed class EmailVerifyEvent {
    data class ShowErrorSnackBar(val title: String, val message: String) : EmailVerifyEvent()
    object RedirectToSuccessScreen : EmailVerifyEvent()
    object RedirectToUserEmpty : EmailVerifyEvent()
}