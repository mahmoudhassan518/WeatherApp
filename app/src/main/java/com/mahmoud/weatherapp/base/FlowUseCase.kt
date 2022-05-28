package com.mahmoud.weatherapp.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<PARAM, TYPE> {
    abstract operator fun invoke(param: PARAM): Flow<TYPE>
}
