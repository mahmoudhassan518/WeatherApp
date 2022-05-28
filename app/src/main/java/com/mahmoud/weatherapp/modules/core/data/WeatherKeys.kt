package com.mahmoud.weatherapp.modules.core.data

object WeatherKeys {

    // Shared keys
    external fun geApiUrl(): String
    external fun geAppId(): String

    init {
        System.loadLibrary("main")
    }
}
