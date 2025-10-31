package com.example.core.viewmodel.authviewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.users.UserModel
import com.example.core.repository.AuthRepository
import com.example.core.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    /*
     Ghi chú:
     - LoginViewModel được tạo bởi Hilt (nhờ @HiltViewModel). Hilt inject
       AuthRepository và UserPreferencesRepository thông qua constructor.
     - Khi người dùng đăng nhập thành công, ViewModel lưu trạng thái bằng
       userPreferencesRepository.saveStatePreferences(...).
     - Khi khởi tạo ViewModel, ta đọc preferences bằng userPreferencesRepository.getStatePreferences()
       để khôi phục trạng thái "remember" hoặc loại provider (email/facebook/google).
     - Không cần tự tạo instance DataStore/Context trong ViewModel — Hilt cung cấp repository đã
       được cấu hình sẵn.
    */
    private val _message = MutableStateFlow<String>("")
    val message: StateFlow<String> = _message
    private val _user = MutableStateFlow<UserModel?>(null)
    val user: StateFlow<UserModel?> = _user
    // variable
    var isLoading by mutableStateOf(false)
        private set
    // remember preferences exposed to UI
    var isRemembered by mutableStateOf(false)
        private set
    init {
        // load saved preferences at startup
        viewModelScope.launch {
            userPreferencesRepository.getStatePreferences().onSuccess { prefs ->
                isRemembered = prefs.isRememberLogged
                // optionally load user info if remembered
                if (prefs.isRememberLogged && prefs.userId.isNotBlank()) {
                    _user.value = authRepository.getCurrentUser()
                }
            }
        }
    }

    // provider param indicates login type: "email", "facebook", "google"
    fun signIn(email: String, password: String, rememberMe: Boolean = false) {
        val parentJob = viewModelScope.launch {
            isLoading = true
            val result = authRepository.signInWithEmailPassword(email, password)
            result.onSuccess {
                _message.value = "Đăng nhập thành công"
                // load current user and save preferences
                val current = authRepository.getCurrentUser()
                _user.value = current
                val userId = current.idUser
                // save preference (suspend)
                userPreferencesRepository.saveStatePreferences(rememberMe, userId)
                // update local state
                isRemembered = rememberMe
            }.onFailure {
                _message.value = it.message ?: "Đăng nhập thất bại"
            }
        }
        parentJob.invokeOnCompletion {
            isLoading = false
        }
    }

    fun signUp(
        name: String, email: String, password: String, phone: String, url: String, role: String
    ) {
        val parentJos = viewModelScope.launch {
            isLoading = true
            val result = authRepository.signUp(name, email, password, url, role)
            result.onSuccess {
                _message.value = "Đăng ký thành cônh"
            }.onFailure {
                _message.value = "Đăng ký thất bại"
            }
        }
        parentJos.invokeOnCompletion {
            isLoading = false
        }
    }
    fun signOut() {
        val parentJob = viewModelScope.launch {
            isLoading = true
            authRepository.signOut()
            userPreferencesRepository.clearPreferences()
        }
        parentJob.invokeOnCompletion {
            isLoading = false
        }
    }
}