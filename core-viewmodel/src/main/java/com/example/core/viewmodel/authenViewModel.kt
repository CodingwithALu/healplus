package com.example.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.dataStore.NetworkManager
import com.example.core.model.users.UserModel
import com.example.core.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel1 @Inject constructor(
    private val networkManager: NetworkManager,
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _user = MutableStateFlow(UserModel.empty())
    val user: StateFlow<UserModel> = _user

    init {
        fetchUser()
    }

    fun fetchUser() {
        viewModelScope.launch {
            try {
                if (!networkManager.checkConnection()) {
                    return@launch
                }
                withContext(NonCancellable) {
                    val result = authRepository.fetchUserFromData()
                    _user.value = result
                }
            } catch (e: Exception) {
            }

        }
    }
}