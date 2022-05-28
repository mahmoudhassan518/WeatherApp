package com.mahmoud.weatherapp.modules.weather.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    @SerializedName("coord")
    val coordinators: Coordinators,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

data class Clouds(
    val all: Int
)
data class Coordinators(
    val lat: Double,
    val lon: Double
)

data class Main(
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)

data class Sys(
    val country: String,
    val id: Int,
    val message: Double,
    val sunrise: Long,
    val sunset: Long,
    val type: Int
)

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)

data class Wind(
    val deg: Int,
    val speed: Double
)