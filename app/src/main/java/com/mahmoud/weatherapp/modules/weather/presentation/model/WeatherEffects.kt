package com.mahmoud.weatherapp.modules.weather.presentation.model

import androidx.annotation.StringRes

sealed class WeatherEffects {
    data class ShowWeatherError(@StringRes val message: Int) : WeatherEffects()
}
