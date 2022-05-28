package com.mahmoud.weatherapp.modules.location.domain.repository

import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getUserLocation(): Flow<LocationEntity>
}
