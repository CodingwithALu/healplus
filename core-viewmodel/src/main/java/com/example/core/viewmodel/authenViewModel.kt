package com.example.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dataStore.NetworkManager
import com.example.core.repository.AppLoginRepository
import com.example.core.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel1 @Inject constructor(
    private val networkManager: NetworkManager,
    private val authRepository: AuthRepository,
    private val appLoginRepository: AppLoginRepository
) : ViewModel() {
    private val _appLogin = MutableStateFlow<Boolean>(false)
    val appLogin: StateFlow<Boolean> = _appLogin
    init {
        loadAppLogin()
    }
    fun loadAppLogin(){
        viewModelScope.launch {
            _appLogin.value = appLoginRepository.appLogin()
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.signOut()
            try {
                appLoginRepository.cleanAppLogin()
            } catch (_: Exception) {
            }
        }
    }
}