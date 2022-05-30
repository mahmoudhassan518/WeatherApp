package com.mahmoud.weatherapp.modules.location.domain.interactor

import com.mahmoud.weatherapp.base.SuspendUseCase
import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity
import com.mahmoud.weatherapp.modules.location.domain.repository.LocationRepository
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val repository: LocationRepository,
) : SuspendUseCase<Unit, LocationEntity> {

    override suspend fun invoke(param: Unit): LocationEntity =
        repository.getUserLocation()
}
