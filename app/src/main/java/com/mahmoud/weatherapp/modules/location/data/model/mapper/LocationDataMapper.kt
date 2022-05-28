package com.mahmoud.weatherapp.modules.location.data.model.mapper

import com.mahmoud.weatherapp.modules.location.data.model.LocationDto
import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity

fun LocationDto.toEntity(): LocationEntity =
    LocationEntity(
        lat = lat, lon = lon
    )