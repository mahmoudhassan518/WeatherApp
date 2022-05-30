package com.mahmoud.weatherapp.modules.location.data.repository

import com.mahmoud.weatherapp.modules.location.data.model.mapper.toEntity
import com.mahmoud.weatherapp.modules.location.data.source.FusedLocationSource
import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity
import com.mahmoud.weatherapp.modules.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val source: FusedLocationSource) :
    LocationRepository {

    override suspend fun getUserLocation():LocationEntity =
        source.getUserLocation().toEntity()
}