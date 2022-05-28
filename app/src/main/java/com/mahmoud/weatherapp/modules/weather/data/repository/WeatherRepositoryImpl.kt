package com.mahmoud.weatherapp.modules.weather.data.repository

import com.ksa.unticovid.modules.core.di.IODispatcher
import com.mahmoud.weatherapp.base.BaseRepository
import com.mahmoud.weatherapp.modules.weather.data.model.mapper.toEntity
import com.mahmoud.weatherapp.modules.weather.data.source.WeatherRemoteDS
import com.mahmoud.weatherapp.modules.weather.domain.exception.InvalidApiKeyException
import com.mahmoud.weatherapp.modules.weather.domain.exception.InvalidateQuerySearchException
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
import com.mahmoud.weatherapp.modules.weather.domain.model.param.WeatherParam
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    @IODispatcher ioDispatcher: CoroutineDispatcher,
    private val source: WeatherRemoteDS
) : WeatherRepository, BaseRepository(ioDispatcher) {
    override fun getCityWeather(city: String): Flow<WeatherEntity> =
        requestHandler(buildWeatherErrorMap()) {
            source.getCityWeather(city).toEntity()
        }

    override fun getCoordinatorWeather(param: WeatherParam): Flow<WeatherEntity> =
        requestHandler(buildWeatherErrorMap()) {
            source.getCoordinatorWeather(param).toEntity()
        }


    private fun buildWeatherErrorMap() = mapOf(
        401 to InvalidApiKeyException,
        400 to InvalidateQuerySearchException,
    )
}