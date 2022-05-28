package com.mahmoud.weatherapp.modules.weather.presentation.model


data class WeatherUIState(
    val loading: Boolean = false,
    val error: Int? = null,
    val weather: WeatherDataUIState? = null,
)

data class WeatherDataUIState(
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val weather: String?,
    val weatherDescription: String?,
    val showWeather: Boolean,
    val temperature: Double,
    val maximumTemperature: Double,
    val minimumTemperature: Double,
    val atmosphericPressure: Int,
    val humidityPercentage: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val cloudsPercentage: Int,
    val sunriseTime: String,
    val sunsetTime: String,
)