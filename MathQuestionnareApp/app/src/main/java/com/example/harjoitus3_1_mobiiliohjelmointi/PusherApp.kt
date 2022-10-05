package com.example.harjoitus3_1_mobiiliohjelmointi

import android.app.Application
import timber.log.Timber

class PusherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}