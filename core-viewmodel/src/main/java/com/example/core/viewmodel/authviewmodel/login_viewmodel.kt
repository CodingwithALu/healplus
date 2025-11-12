package com.example.core.viewmodel.authviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.users.UserModel
import com.example.core.repository.AuthRepository
import com.example.core.repository.EmailVerifyEvent
import com.example.core.repository.UserPreferencesRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth,
    private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {
    private var _emailVerify = MutableStateFlow<EmailVerifyEvent?>(null)
    val emailVerify: StateFlow<EmailVerifyEvent?> = _emailVerify
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message
    private val _user = MutableStateFlow(UserModel.empty())
    val user: StateFlow<UserModel> = _user
    // variable
    var isLoading by mutableStateOf(false)
        private set
    init {
        viewModelScope.launch {
            userPreferencesRepository.getStatePreferences().onSuccess { prefs ->
                _user.value.email = prefs.emailUser
                _user.value.password = prefs.password
            }
        }
    }
    // provider param
    fun signIn(email: String, password: String, rememberMe: Boolean = false) {
        viewModelScope.launch {
            isLoading = true
            try {
                async { authRepository.signInWithEmailPassword(email, password) }.await()
                if (rememberMe){
                    async { userPreferencesRepository.saveStatePreferences(email, password)}.await()
                }
                auth.currentUser?.reload()?.await()
                if (auth.currentUser?.isEmailVerified == true) {
                    _emailVerify.value = EmailVerifyEvent.RedirectToVerifyScreen
                } else {
                    _emailVerify.value = EmailVerifyEvent.RedirectToSuccessScreen
                }
            }catch (e: Exception){
                throw IllegalArgumentException(e.message)
            }finally {
                isLoading = false
            }
        }
    }
}