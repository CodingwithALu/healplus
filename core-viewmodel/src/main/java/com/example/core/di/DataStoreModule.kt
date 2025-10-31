package com.example.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.dataStore.rememberDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    /*
     Đây là module Hilt cung cấp DataStore<Preferences>:
     - @Provides và @Singleton đảm bảo 1 instance chung cho toàn app.
     - @ApplicationContext truyền Context của Application vào để lấy delegate.
     - Các repository có constructor parameter DataStore<Preferences> sẽ được Hilt inject.
    */
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.rememberDataStore
}
