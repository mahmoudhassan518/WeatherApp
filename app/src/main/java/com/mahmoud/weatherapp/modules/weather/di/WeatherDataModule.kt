package com.mahmoud.weatherapp.modules.weather.di

import com.mahmoud.weatherapp.modules.weather.data.repository.WeatherRepositoryImpl
import com.mahmoud.weatherapp.modules.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class WeatherDataModule {

    @Binds
    internal abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}
