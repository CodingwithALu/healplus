package com.example.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    private companion object {
        val EMAIL_USER = stringPreferencesKey("email_user")
        val PASS_WORS = stringPreferencesKey("password_user")
    }

    // save isRemember, userId and provider (run on IO dispatcher)
    suspend fun saveStatePreferences(emailUser: String, passwordUser: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[EMAIL_USER] = emailUser
                preferences[PASS_WORS] = passwordUser
            }
        }
    }

    // read current saved preferences (runs on IO dispatcher)
    suspend fun getStatePreferences(): Result<StatePreferences> =
        try {
            withContext(Dispatchers.IO) {
                val pref = dataStore.data.first()
                val userId = pref[EMAIL_USER] ?: ""
                val passwordUser = pref[PASS_WORS] ?: ""
                Result.success(StatePreferences(userId, passwordUser))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    suspend fun clearPreferences(): Result<Unit> =
        try {
            withContext(Dispatchers.IO){
                dataStore.edit { preferences ->
                    preferences.clear()
                }
            }
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }
}

data class StatePreferences(
    val emailUser: String,
    val password: String
)