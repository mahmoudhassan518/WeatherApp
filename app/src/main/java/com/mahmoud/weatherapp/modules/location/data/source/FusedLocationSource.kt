package com.mahmoud.weatherapp.modules.location.data.source

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.mahmoud.weatherapp.modules.location.data.model.LocationDto
import com.mahmoud.weatherapp.modules.location.domain.exception.FailedToGetLocationException
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class FusedLocationSource @Inject constructor(private val client: FusedLocationProviderClient) {

    suspend fun getUserLocation(): LocationDto {
        val location = client.awaitLastLocation()
        return LocationDto(lat = location.latitude, lon = location.longitude)
    }

    @SuppressLint("MissingPermission")
    suspend fun FusedLocationProviderClient.awaitLastLocation(): Location =
        suspendCancellableCoroutine { continuation ->
            lastLocation.addOnSuccessListener { location ->
                continuation.resume(location) {}
            }.addOnFailureListener {
                continuation.resumeWithException(FailedToGetLocationException)
            }
        } }
