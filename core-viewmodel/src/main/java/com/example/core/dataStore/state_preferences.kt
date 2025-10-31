package com.example.core.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/*
 filepath-level DataStore delegate:
 - preferencesDataStore tạo 1 DataStore<Preferences> singleton gắn với Context.
 - Module Hilt (DataStoreModule) sẽ trả về context.rememberDataStore để inject
   vào các repository (như UserPreferencesRepository).
 - Khi cần đọc/ghi, repository dùng dataStore.data (Flow) hoặc dataStore.edit { }.
 - Lưu ý: delegate phải ở file-level (top-level) và dùng Context extension.
*/
val Context.rememberDataStore: DataStore<Preferences> by preferencesDataStore(name = "remember_in_login")
