package com.mahmoud.weatherapp.modules.weather.domain.interactor

import com.mahmoud.weatherapp.base.FlowUseCase
import com.mahmoud.weatherapp.modules.location.domain.interactor.GetUserLocationUseCase
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.model.param.WeatherParam
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val getUserLocationUseCase: GetUserLocationUseCase
) : FlowUseCase<Unit, WeatherEntity> {

    override suspend fun invoke(param: Unit): Flow<WeatherEntity> {
        val location = getUserLocationUseCase(Unit)
        return repository.getUserWeather(WeatherParam(lat = location.lat, lon = location.lon))
    }

}
