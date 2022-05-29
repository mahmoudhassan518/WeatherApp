package com.mahmoud.weatherapp.modules.weather.data.source

import com.mahmoud.weatherapp.modules.core.data.ApiService
import com.mahmoud.weatherapp.modules.weather.domain.model.param.WeatherParam
import javax.inject.Inject

class WeatherRemoteDS @Inject constructor(private val apiService: ApiService) {

    suspend fun getCityWeather(city: String) =
        apiService.getWeather(city.buildWeatherCityParam())

    private fun String.buildWeatherCityParam() =
        mapOf(
            "q" to this
        )

    suspend fun getCoordinatorWeather(param: WeatherParam) =
        apiService.getWeather(param.buildWeatherCityParam())

    private fun WeatherParam.buildWeatherCityParam() =
        mapOf(
            "lat" to lat.toString(),
            "lon" to lon.toString()
        )
}
