package com.mahmoud.weatherapp.modules.location.domain.repository

import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity

interface LocationRepository {

    suspend fun getUserLocation(): LocationEntity
}
