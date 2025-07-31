package com.example.core.viewmodel.Repository

import com.example.core.model.users.UserAuthModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    suspend fun signInWithEmailPassword(email: String, password: String): Result<AuthResult> = runCatching {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun signUp(
        name: String, email: String, password: String, phone: String, url: String, role: String
    ): Result<UserAuthModel> = runCatching {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val userId = result.user?.uid ?: throw Exception("User ID is null")
        val userModel = UserAuthModel(
            idauth = userId,
            name = name,
            email = email,
            password = password,
            phone = phone,
            url = url,
            role = role
        )
        db.collection("users").document(userId).set(userModel).await()
        userModel
    }

    suspend fun getCurrentUser(): UserAuthModel? {
        val userId = auth.currentUser?.uid ?: return null
        val document = db.collection("users").document(userId).get().await()
        return document.toObject(UserAuthModel::class.java)
    }

    suspend fun updateUser(fields: Map<String, Any?>): Result<Boolean> = runCatching {
        val userId = auth.currentUser?.uid ?: throw Exception("User not logged in")
        db.collection("users").document(userId).update(fields).await()
        true
    }

    fun signOut() {
        auth.signOut()
    }
}