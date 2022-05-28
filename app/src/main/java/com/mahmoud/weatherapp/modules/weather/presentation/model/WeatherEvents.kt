package com.mahmoud.weatherapp.modules.weather.presentation.model

sealed class WeatherEvents {
    data class GetCityWeather(val city: String) : WeatherEvents()
    object GetUserWeather : WeatherEvents()
}
