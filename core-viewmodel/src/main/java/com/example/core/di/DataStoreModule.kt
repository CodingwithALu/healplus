package com.example.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.dataStore.rememberDataStore
import com.example.core.repository.FirstTimeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.rememberDataStore

    @Provides
    @Singleton
    fun provideIsFirstTime(dataStore: DataStore<Preferences>): FirstTimeRepository {
        return FirstTimeRepository(dataStore)
    }
}
