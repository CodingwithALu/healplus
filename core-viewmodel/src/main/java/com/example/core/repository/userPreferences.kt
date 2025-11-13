package com.example.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val EMAIL_USER = stringPreferencesKey("email_user")
        val PASS_WORS = stringPreferencesKey("password_user")
    }
    suspend fun saveStatePreferences(emailUser: String, passwordUser: String) {
        withContext(Dispatchers.IO) {
            val pref = dataStore.data.first()
            val currentEmail = pref[EMAIL_USER] ?: ""
            val currentPassword = pref[PASS_WORS] ?: ""
            if (currentEmail != emailUser || currentPassword != passwordUser) {
                dataStore.edit { preferences ->
                    preferences.clear()
                    preferences[EMAIL_USER] = emailUser
                    preferences[PASS_WORS] = passwordUser
                }
            }
        }
    }
    suspend fun getStatePreferences(): StatePreferences {
        val result = StatePreferences.empty()
        withContext(Dispatchers.IO) {
            val pref = dataStore.data.first()
            result.emailUser = pref[EMAIL_USER] ?: ""
            result.password = pref[PASS_WORS] ?: ""
        }
        return result
    }
    suspend fun clearPreferences() {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }
}
data class StatePreferences(
    var emailUser: String,
    var password: String
) {
    companion object {
        fun empty() = StatePreferences(
            emailUser = "",
            password = ""
        )
    }
}