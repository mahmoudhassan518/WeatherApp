package com.mahmoud.weatherapp.modules.location.data.source

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.mahmoud.weatherapp.modules.location.data.model.LocationDto
import com.mahmoud.weatherapp.modules.location.domain.exception.FailedToGetLocationException
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class FusedLocationSource @Inject constructor(private val client: FusedLocationProviderClient) {

    @SuppressLint("MissingPermission")
    fun getUserLocation() = callbackFlow {
        client.lastLocation.addOnSuccessListener { location: Location? ->
            // Got last known location. In some rare situations this can be null.
            if (location == null)
                throw  FailedToGetLocationException
            else
                trySend(LocationDto(lat = location.latitude, lon = location.longitude))

        }
    }

}