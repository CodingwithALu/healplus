package com.example.healplus

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.initialize
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealPlusApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(context = this)
//        Firebase.appCheck.installAppCheckProviderFactory(
//            PlayIntegrityAppCheckProviderFactory.getInstance(),
//        )
        val debugProviderFactory = DebugAppCheckProviderFactory.getInstance()
        FirebaseAppCheck.getInstance().installAppCheckProviderFactory(debugProviderFactory)
    }
}