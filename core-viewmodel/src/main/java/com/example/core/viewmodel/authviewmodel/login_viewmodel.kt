package com.example.core.viewmodel.authviewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.repository.AppLoginRepository
import com.example.core.repository.AuthRepository
import com.example.core.repository.EmailVerifyEvent
import com.example.core.repository.StatePreferences
import com.example.core.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val appLoginRepository: AppLoginRepository
) : ViewModel() {
    private var _emailVerify = MutableStateFlow<EmailVerifyEvent?>(null)
    val emailVerify: StateFlow<EmailVerifyEvent?> = _emailVerify
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message
    private val _user = MutableStateFlow(StatePreferences.empty())
    val user: StateFlow<StatePreferences> = _user

    // variable
    var isLoading by mutableStateOf(false)
        private set

    init {
        rememberUser()
    }

    // provider param
    fun rememberUser() {
        viewModelScope.launch {
            _user.value = userPreferencesRepository.getStatePreferences()
        }
    }

    fun signIn(email: String, password: String, rememberMe: Boolean = false) {
        viewModelScope.launch {
            isLoading = true
            try {
                async { authRepository.signInWithEmailPassword(email, password) }.await()
                if (rememberMe) {
                    async {
                        userPreferencesRepository.saveStatePreferences(
                            email,
                            password
                        )
                    }.await()
                }
                Log.d("Login", "${userPreferencesRepository.getStatePreferences().emailUser}, ${userPreferencesRepository.getStatePreferences().password}")
                if (authRepository.verifyEmail()) {
                    appLoginRepository.setFirstTimeLogin(isFirstTime = true)
                    Log.d("isLogin", "check:${appLoginRepository.appLogin()}")
                    _emailVerify.value = EmailVerifyEvent.RedirectToSuccessScreen
                } else {
                    _emailVerify.value = EmailVerifyEvent.RedirectToVerifyScreen
                }
            } catch (e: Exception) {
                throw IllegalArgumentException(e.message)
            } finally {
                isLoading = false
            }
        }
    }


}