package com.example.core.viewmodel.authviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.api.ApiResponse
import com.example.core.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel(){
    private var _result = MutableStateFlow(ApiResponse.empty())
    val result: StateFlow<ApiResponse> = _result
    var isLoading by mutableStateOf(false)
        private set

    fun createUser(name: String, email: String, password: String){
        viewModelScope.launch {
            try {
                isLoading = true
                val userResult = authRepository.createUser(name, email, password)
                _result.value = userResult
            }catch (e: Exception){
                throw IllegalArgumentException(e.message)
            }finally {
                isLoading = false
            }
        }
    }

}