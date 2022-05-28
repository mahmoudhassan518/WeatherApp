package com.mahmoud.weatherapp.modules.weather.domain.repository

import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.model.param.WeatherParam
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    fun getCityWeather(city: String): Flow<WeatherEntity>
    fun getCoordinatorWeather(param: WeatherParam): Flow<WeatherEntity>
}