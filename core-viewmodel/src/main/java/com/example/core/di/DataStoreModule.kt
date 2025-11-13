package com.example.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.core.dataStore.NetworkManager
import com.example.core.dataStore.appLoginDataStore
import com.example.core.dataStore.rememberDataStore
import com.example.core.repository.AppLoginRepository
import com.example.core.repository.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    @Named("user_pref")
    fun providePreferencesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.rememberDataStore
    @Provides
    @Singleton
    @Named("app_login")
    fun provideAppLoginDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.appLoginDataStore
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        @Named("user_pref") dataStore: DataStore<Preferences>
    ): UserPreferencesRepository {
        return UserPreferencesRepository(dataStore)
    }
    @Provides
    @Singleton
    fun provideAppLoginRepository(
        @Named("app_login") dataStore: DataStore<Preferences>
    ): AppLoginRepository {
        return AppLoginRepository(dataStore)
    }
    //network
    @Provides
    @Singleton
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }
}
