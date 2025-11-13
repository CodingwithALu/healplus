package com.example.core.viewmodel.authviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.repository.AppLoginRepository
import com.example.core.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth,
    private val appLoginRepository: AppLoginRepository
) : ViewModel() {
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)
    val uiEvent: StateFlow<UiEvent?> = _uiEvent
    private var timerJob: Job? = null
    fun sendEmailVerification() {
        viewModelScope.launch {
            try {
                withContext(NonCancellable){
                    authRepository.sendEmailVerification()
                }
                _uiEvent.value = UiEvent.ShowSuccessSnackBar("Thông báo", "Đã gửi email xác thực!")
            } catch (e: Exception) {
                _uiEvent.value = UiEvent.ShowErrorSnackBar("Lỗi", "Gửi email thất bại: ${e.message}")
            }
        }
    }
    fun checkEmailVerificationStatus() {
        viewModelScope.launch {
            async { auth.currentUser?.reload() }.await()
            val user = auth.currentUser
            if (user?.isEmailVerified == true) {
                _uiEvent.value = UiEvent.RedirectToSuccessScreen
                appLoginRepository.setFirstTimeLogin(true)
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}

sealed class UiEvent {
    data class ShowSuccessSnackBar(val title: String, val message: String) : UiEvent()
    data class ShowErrorSnackBar(val title: String, val message: String) : UiEvent()
    object RedirectToSuccessScreen : UiEvent()
}