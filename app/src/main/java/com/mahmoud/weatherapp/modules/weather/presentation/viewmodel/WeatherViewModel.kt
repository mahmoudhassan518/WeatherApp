package com.mahmoud.weatherapp.modules.weather.presentation.viewmodel

import com.ksa.unticovid.modules.core.di.MainDispatcher
import com.mahmoud.weatherapp.R
import com.mahmoud.weatherapp.base.BaseViewModel
import com.mahmoud.weatherapp.modules.location.domain.exception.FailedToGetLocationException
import com.mahmoud.weatherapp.modules.weather.domain.exception.EmptySearchCityException
import com.mahmoud.weatherapp.modules.weather.domain.exception.InvalidApiKeyException
import com.mahmoud.weatherapp.modules.weather.domain.exception.InvalidSearchCityLengthException
import com.mahmoud.weatherapp.modules.weather.domain.exception.InvalidateQuerySearchException
import com.mahmoud.weatherapp.modules.weather.domain.interactor.GetCityWeatherUseCase
import com.mahmoud.weatherapp.modules.weather.domain.interactor.GetCoordinatorWeatherUseCase
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherEffects
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherEvents
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherUIState
import com.mahmoud.weatherapp.modules.weather.presentation.model.mapper.toUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    @MainDispatcher val mainDispatcher: CoroutineDispatcher,
    private val getCoordinatorWeatherUseCase: GetCoordinatorWeatherUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : BaseViewModel<WeatherUIState, WeatherEffects, WeatherEvents>(
    mainDispatcher,
    WeatherUIState()
) {


    override fun transform(event: WeatherEvents) {
        when (event) {
            is WeatherEvents.GetCityWeather -> getCityWeather(event.city)
            is WeatherEvents.GetUserWeather -> getUserWeather()
        }
    }

    private fun getCityWeather(city: String) =
        launchBlock(onStart = { donOnStart() }, onError = { it.doOnError() }) {
            getCityWeatherUseCase(city).collectLatest { data ->
                setState {
                    copy(
                        loading = false,
                        weather = data.toUI()
                    )
                }
            }
        }

    private fun getUserWeather() =
        launchBlock(onStart = { donOnStart() }, onError = { it.doOnError() }) {
            getCoordinatorWeatherUseCase(Unit).collectLatest { data ->
                setState {
                    copy(
                        loading = false,
                        weather = data.toUI()
                    )
                }
            }
        }

    private fun donOnStart() =
        setState {
            copy(
                loading = true,
                error = null
            )
        }

    private fun Throwable.doOnError() =
        if (currentState.weather == null)
            handleErrorState()
        else
            handleErrorEffect()

    private fun Throwable.handleErrorEffect() {
        setEffect { WeatherEffects.ShowWeatherError(handleError()) }
        setState { copy(loading = false) }
    }

    private fun Throwable.handleErrorState() {
        setState {
            copy(
                loading = false,
                error = handleError()
            )
        }
    }


    private fun Throwable.handleError(): Int =
        when (this) {
            is FailedToGetLocationException -> R.string.msg_failed_to_get_user_location
            is EmptySearchCityException -> R.string.msg_failed_to_get_user_location
            is InvalidSearchCityLengthException -> R.string.msg_search_city_length_validation
            is InvalidateQuerySearchException -> R.string.msg_invalidate_query_search
            is InvalidApiKeyException -> R.string.msg_invalidate_query_search
            else -> R.string.msg_something_went_wrong
        }


}
