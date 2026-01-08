package com.example.core.viewmodel

import android.net.Uri
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
class UserViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val networkManager: NetworkManager,
) : ViewModel() {
    private var _user = MutableStateFlow(UserModel.empty())
    val user: StateFlow<UserModel> = _user

    // simple UI flags (optional but useful)
    private val _isUpdating = MutableStateFlow(false)
    val isUpdating: StateFlow<Boolean> = _isUpdating

    private val _updateError = MutableStateFlow<String?>(null)
    val updateError: StateFlow<String?> = _updateError

    init {
        fetchUser()
    }

    fun fetchUser() {
        viewModelScope.launch {
            try {
                if (!networkManager.checkConnection()) return@launch
                withContext(NonCancellable) {
                    val result = authRepository.fetchUserFromData()
                    _user.value = result
                }
            } catch (_: Exception) {
                // keep silent as before
            }
        }
    }

    // FIX: nhận 1 object UserModel (+ Uri ảnh nếu có)
    fun updateUserAccount(userModel: UserModel, selectedImageUri: Uri? = null) {
        viewModelScope.launch {
            _isUpdating.value = true
            _updateError.value = null
            try {
                if (!networkManager.checkConnection()) return@launch

                // IMPORTANT:
                // - selectedImageUri: Uri ảnh local (content://...) do user chọn
                // - Cloudinary URL (https://...) chỉ có sau khi upload thành công.
                // TODO: upload Cloudinary if selectedImageUri != null, lấy secure_url
                // val finalModel = if (selectedImageUri != null) userModel.copy(url = secureUrl) else userModel
                // TODO: authRepository.updateProfile(finalModel)

                // Cập nhật local state để UI phản ánh ngay
                _user.value = userModel
            } catch (e: Exception) {
                _updateError.value = "Cập nhật thất bại: ${e.message ?: "Unknown error"}"
            } finally {
                _isUpdating.value = false
            }
        }
    }
}