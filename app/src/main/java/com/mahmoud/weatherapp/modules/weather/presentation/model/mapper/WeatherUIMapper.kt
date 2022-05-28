package com.mahmoud.weatherapp.modules.weather.presentation.model.mapper

import android.text.format.DateFormat
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherDataUIState

fun WeatherEntity.toUI() = WeatherDataUIState(
    cityName = cityName,
    latitude = latitude,
    longitude = longitude,
    weather = weather,
    weatherDescription = weatherDescription,
    showWeather = (weather != null).and(weatherDescription != null),
    temperature = temperature,
    maximumTemperature = maximumTemperature,
    minimumTemperature = minimumTemperature,
    atmosphericPressure = atmosphericPressure,
    humidityPercentage = humidityPercentage,
    visibility = visibility,
    windSpeed = windSpeed,
    windDirection = windDirection,
    cloudsPercentage = cloudsPercentage,
    sunriseTime = sunriseTime.toDate(),
    sunsetTime = sunsetTime.toDate(),
)

fun Long.toDate(): String =
    DateFormat.format("HH:mm:ss a", this).toString()
