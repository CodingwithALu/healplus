package com.example.healplus.feature.utils.constants

import com.example.core.model.users.UserAuthModel
import com.example.healplus.utils.constants.ErrorType
import com.example.healplus.utils.constants.ScreenDestination

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: UserAuthModel? = null, val destination: ScreenDestination) : AuthState()
    object LogoutSuccess : AuthState()
    data class Error(val message: String, val errorType: ErrorType = ErrorType.GENERAL) : AuthState()
    object EmailVerificationSent : AuthState()
    object PasswordResetSent : AuthState()
}