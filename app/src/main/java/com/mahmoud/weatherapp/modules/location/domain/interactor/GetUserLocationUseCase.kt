package com.mahmoud.weatherapp.modules.location.domain.interactor

import com.mahmoud.weatherapp.base.FlowUseCase
import com.mahmoud.weatherapp.modules.location.domain.model.LocationEntity
import com.mahmoud.weatherapp.modules.location.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserLocationUseCase @Inject constructor(
    private val repository: LocationRepository,
) : FlowUseCase<Unit, LocationEntity> {

    override fun invoke(param: Unit): Flow<LocationEntity> =
        repository.getUserLocation()
}
