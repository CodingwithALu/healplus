package com.example.healplus

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HealPlusApp : Application(){
    override fun onCreate() {
        super.onCreate()
        Log.d("Start", "Begin start")
    }
}