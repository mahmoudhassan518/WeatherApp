package com.mahmoud.weatherapp.modules.weather.domain.interactor

import com.mahmoud.weatherapp.base.FlowUseCase
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) : FlowUseCase<String, WeatherEntity>() {

    override fun invoke(param: String): Flow<WeatherEntity> =
        repository.getCityWeather(param)
}