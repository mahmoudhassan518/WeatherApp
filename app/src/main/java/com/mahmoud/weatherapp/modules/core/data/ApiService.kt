package com.mahmoud.weatherapp.modules.core.data

import com.mahmoud.weatherapp.modules.weather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("weather")
    suspend fun getWeather(@QueryMap param: Map<String, String>): WeatherResponse
}
