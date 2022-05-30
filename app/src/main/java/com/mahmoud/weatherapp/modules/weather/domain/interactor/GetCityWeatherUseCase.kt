package com.mahmoud.weatherapp.modules.weather.domain.interactor

import com.mahmoud.weatherapp.base.FlowUseCase
import com.mahmoud.weatherapp.modules.weather.domain.exception.EmptySearchCityException
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) : FlowUseCase<String, WeatherEntity> {

    override suspend fun invoke(param: String): Flow<WeatherEntity> =
        if (param.isEmpty())
            throw EmptySearchCityException
        else
            repository.getCityWeather(param)
}