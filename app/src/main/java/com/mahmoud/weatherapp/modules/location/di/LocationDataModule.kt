package com.mahmoud.weatherapp.modules.location.di

import com.mahmoud.weatherapp.modules.location.data.repository.LocationRepositoryImpl
import com.mahmoud.weatherapp.modules.location.domain.repository.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class LocationDataModule {

    @Binds
    internal abstract fun bindLocationRepository(impl: LocationRepositoryImpl): LocationRepository
}
