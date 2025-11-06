package com.example.core.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @ApplicationContext private val context: Context
){
    /*
     Ghi chú tích hợp với Hilt:
     - DataStore được cung cấp bởi DataStoreModule (Hilt) nên Hilt inject trực tiếp
       DataStore<Preferences> vào constructor.
     - Repository này là @Singleton để giữ trạng thái chung.
     - Từ ViewModel đánh dấu @HiltViewModel, Hilt sẽ inject UserPreferencesRepository
       khi ViewModel được tạo (bằng HiltViewModelFactory).
     - Sử dụng các key và dataStore.edit/read để lưu/đọc trạng thái đăng nhập.
    */

    private companion object {
        val IS_REMEMBER_LOGGED_IN = booleanPreferencesKey("is_remember_logged_in")
        val USER_ID = stringPreferencesKey("user_id")
    }

    // save isRemember, userId and provider (run on IO dispatcher)
    suspend fun saveStatePreferences(isRemember: Boolean, userId: String) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
                preferences[IS_REMEMBER_LOGGED_IN] = isRemember
                preferences[USER_ID] = userId
            }
        }
    }

    // read current saved preferences (runs on IO dispatcher)
    suspend fun getStatePreferences(): Result<StatePreferences> =
        try {
            withContext(Dispatchers.IO) {
                val pref = dataStore.data.first()
                val isRemember = pref[IS_REMEMBER_LOGGED_IN] ?: false
                val userId = pref[USER_ID] ?: ""
                Result.success(StatePreferences(isRemember, userId))
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
    val isRememberLogged: Boolean,
    val userId: String
)