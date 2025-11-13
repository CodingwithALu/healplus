package com.example.core.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
val Context.rememberDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")
val Context.appLoginDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_login")
