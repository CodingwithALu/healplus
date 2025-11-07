package com.example.core.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirstTimeRepository (
    private val dataStore: DataStore<Preferences>
){
    companion object {
        private val FIRST_TIME_LOGIN = booleanPreferencesKey("first_time_login")
    }

    suspend fun setFirstTimeLogin(isFirstTime: Boolean) {
        dataStore.edit { preferences ->
            preferences[FIRST_TIME_LOGIN] = isFirstTime
        }
    }

    fun isFirstTimeLogin(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[FIRST_TIME_LOGIN] ?: true // Default: true nếu chưa từng set
        }
}