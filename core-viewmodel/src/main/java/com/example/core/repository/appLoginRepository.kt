package com.example.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class AppLoginRepository (
    private val dataStore: DataStore<Preferences>
){
    companion object {
        private val FIRST_TIME_LOGIN = booleanPreferencesKey("app_login")
    }

    suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[FIRST_TIME_LOGIN] = isFirstTime
        }
    }

    suspend fun appLogin(): Boolean {
        return dataStore.data.map { preferences ->
            preferences[FIRST_TIME_LOGIN] ?: false
        }.first()
    }
    suspend fun cleanAppLogin() {
        dataStore.edit { preferences ->
            preferences.clear()
            preferences[FIRST_TIME_LOGIN] = false
        }
    }
}