package com.mahmoud.weatherapp.modules.weather.domain.model.entity

data class WeatherEntity(
    val cityName: String,
    val latitude: Double,
    val longitude: Double,
    val weather: String?,
    val weatherDescription: String?,
    val temperature: Double,
    val maximumTemperature: Double,
    val minimumTemperature: Double,
    val atmosphericPressure: Int,
    val humidityPercentage: Int,
    val visibility: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val cloudsPercentage: Int,
    val sunriseTime: Long,
    val sunsetTime: Long,
)