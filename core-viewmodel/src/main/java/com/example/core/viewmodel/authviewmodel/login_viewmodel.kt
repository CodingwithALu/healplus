//package com.example.core.viewmodel.authviewmodel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.core.model.users.UserAuthModel
//import com.example.core.viewmodel.Repository.AuthRepository
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import javax.inject.Inject
//
//@HiltViewModel
//class LoginViewModel @Inject constructor(
//    private val authRepository: AuthRepository,
//    private val userRepository: UserRepository,
//    private val networkManager: NetworkManager
//) : ViewModel() {
//    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
//    val authState: StateFlow<AuthState> = _authState
//    private val _user = MutableStateFlow<UserAuthModel?>(null)
//    val user: StateFlow<UserAuthModel?> = _user
//    fun signIn(email: String, password: String, rememberMe: Boolean = false) {
//        viewModelScope.launch {
//            _authState.value = AuthState.Loading
//            val result = authRepository.signInWithEmailPassword(email, password)
//            result.onSuccess {
//                _authState.value = AuthState.Success
//                loadCurrentUser()
//            }.onFailure {
//                _authState.value = AuthState.Error(it.message ?: "Đăng nhập thất bại")
//            }
//        }
//    }
//    fun signUp(
//        name: String, email: String, password: String, phone: String, url: String, role: String
//    ) {
//        viewModelScope.launch {
//            _authState.value = AuthState.Loading
//            val result = authRepository.signUp(name, email, password, phone, url, role)
//            result.onSuccess {
//                _authState.value = AuthState.Success
//                loadCurrentUser()
//            }.onFailure {
//                _authState.value = AuthState.Error(it.message ?: "Đăng ký thất bại")
//            }
//        }
//    }
//    fun loadCurrentUser() {
//        viewModelScope.launch {
//            _user.value = authRepository.getCurrentUser()
//        }
//    }
//    fun signOut() {
//        authRepository.signOut()
//        _authState.value = AuthState.Idle
//        _user.value = null
//    }
//}