package com.example.core.viewmodel.authviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VerifyEmailViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth
): ViewModel(){
    private val _uiEvent = MutableStateFlow<UiEvent?>(null)
    val uiEvent: StateFlow<UiEvent?> = _uiEvent
    private var timerJob: Job? = null
    init {
        setTimerForAutoRedirect()
    }
    fun sendEmailVerification(){
        viewModelScope.launch {
            try {
                async {
                    authRepository.sendEmailVerification()
                }.await()
            } catch(e: Exception){
                throw IllegalArgumentException(e.message)
            }
        }
    }
    fun setTimerForAutoRedirect() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (isActive) {
                try {
                    async { auth.currentUser?.reload() }.await()
                    val user = auth.currentUser
                    if (user?.isEmailVerified == true) {
                        _uiEvent.value = UiEvent.RedirectToSuccessScreen
                        break // stop timer
                    }
                    delay(3000)
                } catch (e: Exception) {
                    _uiEvent.value = UiEvent.ShowErrorSnackBar(
                        title = "On Snap!",
                        message = e.message ?: "Unknown Error"
                    )
                    break
                }
            }
        }
    }

    fun checkEmailVerificationStatus() {
        viewModelScope.launch {
            val user = auth.currentUser
            if (user?.isEmailVerified == true) {
                _uiEvent.value = UiEvent.RedirectToSuccessScreen
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