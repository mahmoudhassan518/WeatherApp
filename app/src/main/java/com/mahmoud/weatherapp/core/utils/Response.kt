package com.mahmoud.weatherapp.core.utils

data class Response<T>(
    val data: T,
    val message: String

)