package com.mahmoud.weatherapp.base

interface SuspendUseCase<PARAM, TYPE> {
    suspend operator fun invoke(param: PARAM): TYPE
}
