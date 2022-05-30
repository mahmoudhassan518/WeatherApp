package com.mahmoud.weatherapp.base

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<PARAM, TYPE> {
    suspend operator fun invoke(param: PARAM): Flow<TYPE>
}
