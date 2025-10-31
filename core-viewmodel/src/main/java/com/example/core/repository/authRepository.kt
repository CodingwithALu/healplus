package com.example.core.repository

import com.example.core.model.users.UserModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {
    // password and email
    suspend fun signInWithEmailPassword(email: String, password: String): Result<AuthResult> = runCatching{
        withContext(Dispatchers.IO) {
          auth.signInWithEmailAndPassword(email, password).await()
        }
    }

    suspend fun signUp(
        name: String, email: String, password: String, url: String, role: String
    ): Result<UserModel> = runCatching {
        withContext(Dispatchers.IO) {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid ?: throw Exception("User ID is null")
            val userModel = UserModel.empty()
            db.collection("users").document(userId).set(userModel).await()
            userModel
        }
    }

    suspend fun getCurrentUser(): UserModel {
        return withContext(Dispatchers.IO){
            val userId = auth.currentUser?.uid ?: return@withContext UserModel.empty()
            val document = db.collection("users").document(userId).get().await()
            document.toObject(UserModel::class.java) as UserModel
        }
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