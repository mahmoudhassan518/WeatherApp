package com.mahmoud.weatherapp.base

import android.app.Application
import com.mahmoud.weatherapp.BuildConfig.DEBUG
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}
