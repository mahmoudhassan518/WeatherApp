package com.mahmoud.weatherapp.modules.weather.data.model.mapper

import com.mahmoud.weatherapp.modules.weather.data.model.WeatherResponse
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity

fun WeatherResponse.toEntity() = WeatherEntity(
    cityName = name,
    latitude = coordinators.lat,
    longitude = coordinators.lon,
    weather = if (weather.isNotEmpty()) weather[0].main else null,
    weatherDescription = if (weather.isNotEmpty()) weather[0].description else null,
    temperature = main.temp,
    maximumTemperature = main.temp_max,
    minimumTemperature = main.temp_min,
    atmosphericPressure = main.pressure,
    humidityPercentage = main.humidity,
    visibility = visibility,
    windSpeed = wind.speed,
    windDirection = wind.deg,
    cloudsPercentage = clouds.all,
    sunriseTime = sys.sunrise,
    sunsetTime = sys.sunset,
)