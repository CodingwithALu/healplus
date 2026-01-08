//package com.example.core.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.example.core.model.chat.Message
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.firestore.FirebaseFirestore
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.tasks.await
//class CheckoutViewModel : ViewModel() {
//    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
//    private val _authState = MutableLiveData<AuthSate>()
//    private val _messages = MutableStateFlow<List<Message>>(emptyList())
//    val authSate: LiveData<AuthSate> = _authState
//    val user: LiveData<UserAuthModel?> = _user
//    val messages: StateFlow<List<Message>> = _messages
//    fun getUserId(): String? {
//        return auth.currentUser?.uid
//    }
//    suspend fun getSuspendingUserFullName(): String? {
//        return try {
//            val userId = auth.currentUser?.uid ?: return null
//            val document = db.collection("users").document(userId).get().await()
//            document.getString("name")
//        } catch (e: Exception) {
//            null
//        }
//    }
//    suspend fun getSuspendingUrl(): String? {
//        return try {
//            val userId = auth.currentUser?.uid ?: return null
//            val document = db.collection("users").document(userId).get().await()
//            document.getString("url")
//        } catch (e: Exception) {
//            null
//        }
//    }
//    suspend fun getSuspendingPoint(): String? {
//        return try {
//            val userId = auth.currentUser?.uid ?: return null
//            val document = db.collection("users").document(userId).get().await()
//            document.getString("bonuspoint")
//        } catch (e: Exception) {
//            null
//        }
//    }
//    suspend fun getSuspendingEmail(): String? {
//        return try {
//            val userId = auth.currentUser?.uid ?: return null
//            val document = db.collection("users").document(userId).get().await()
//            document.getString("email")
//        } catch (e: Exception) {
//            null
//        }
//    }
//    fun getUserPhone(onResult: (String?) -> Unit) {
//        auth.currentUser?.uid?.let { userId ->
//            db.collection("users").document(userId).get()
//                .addOnSuccessListener { onResult(it.getString("phoneNumber")) }
//                .addOnFailureListener { onResult(null) }
//        } ?: onResult(null)
//    }
//    fun getEmail(onResult: (String?) -> Unit) {
//        auth.currentUser?.uid?.let { userId ->
//            db.collection("users").document(userId).get()
//                .addOnSuccessListener { onResult(it.getString("email")) }
//                .addOnFailureListener { onResult(null) }
//        } ?: onResult(null)
//    }
//    fun getUrl(onResult: (String?) -> Unit) {
//        auth.currentUser?.uid?.let { userId ->
//            db.collection("users").document(userId).get()
//                .addOnSuccessListener { onResult(it.getString("url")) }
//                .addOnFailureListener { onResult(null) }
//        } ?: onResult(null)
//    }
//}
//sealed class AuthSate {
//    object Unauthenticated : AuthSate()
//    object Loading : AuthSate()
//    object Admin : AuthSate()
//    object User : AuthSate()
//    data class Error(var message: String) : AuthSate()
//}