package com.mahmoud.weatherapp.modules.weather.domain.interactor

import com.mahmoud.weatherapp.base.FlowUseCase
import com.mahmoud.weatherapp.modules.location.domain.interactor.GetUserLocationUseCase
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.model.param.WeatherParam
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import javax.inject.Inject

class GetCoordinatorWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
    private val getUserLocationUseCase: GetUserLocationUseCase
) : FlowUseCase<Unit, WeatherEntity> {

    override fun invoke(param: Unit): Flow<WeatherEntity> =
        getUserLocationUseCase(Unit).flatMapMerge {
            repository.getCoordinatorWeather(WeatherParam(lat = it.lat, lon = it.lon))
        }
}
