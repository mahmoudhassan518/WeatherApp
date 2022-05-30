package com.mahmoud.weatherapp.modules.weather.presentation.model

data class WeatherUIState(
    val loading: Boolean = false,
    val showLocationError: Boolean = false,
    val error: Int? = null,
    val query: String = "",
    val isRetrySourceCitySearch: Boolean = false,
    val weather: WeatherDataUIState? = null,
)

data class WeatherDataUIState(
    val cityName: String,
    val latitude: String,
    val longitude: String,
    val weather: String?,
    val weatherDescription: String?,
    val showWeather: Boolean,
    val temperature: String,
    val maximumTemperature: String,
    val minimumTemperature: String,
    val atmosphericPressure: String,
    val humidityPercentage: String,
    val visibility: String,
    val windSpeed: String,
    val windDirection: String,
    val cloudsPercentage: String,
    val sunriseTime: String,
    val sunsetTime: String,
)
