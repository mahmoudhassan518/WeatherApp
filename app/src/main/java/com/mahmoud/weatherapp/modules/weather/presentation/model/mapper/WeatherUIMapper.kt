package com.mahmoud.weatherapp.modules.weather.presentation.model.mapper

import android.text.format.DateFormat
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherDataUIState

fun WeatherEntity.toUI() = WeatherDataUIState(
    cityName = cityName,
    latitude = latitude.toString(),
    longitude = longitude.toString(),
    weather = weather,
    weatherDescription = weatherDescription,
    showWeather = (weather != null).and(weatherDescription != null),
    temperature = temperature.toString(),
    maximumTemperature = maximumTemperature.toString(),
    minimumTemperature = minimumTemperature.toString(),
    atmosphericPressure = atmosphericPressure.toString(),
    humidityPercentage = humidityPercentage.toString(),
    visibility = visibility.toString(),
    windSpeed = windSpeed.toString(),
    windDirection = windDirection.toString(),
    cloudsPercentage = cloudsPercentage.toString(),
    sunriseTime = sunriseTime.toDate(),
    sunsetTime = sunsetTime.toDate(),
)

fun Long.toDate(): String =
    DateFormat.format("HH:mm:ss a", this).toString()
