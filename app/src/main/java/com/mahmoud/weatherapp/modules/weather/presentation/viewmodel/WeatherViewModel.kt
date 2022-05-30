package com.mahmoud.weatherapp.modules.weather.presentation.viewmodel

import com.mahmoud.weatherapp.R
import com.mahmoud.weatherapp.base.BaseViewModel
import com.mahmoud.weatherapp.core.di.MainDispatcher
import com.mahmoud.weatherapp.modules.location.domain.exception.FailedToGetLocationException
import com.mahmoud.weatherapp.modules.weather.domain.exception.*
import com.mahmoud.weatherapp.modules.weather.domain.interactor.GetCityWeatherUseCase
import com.mahmoud.weatherapp.modules.weather.domain.interactor.GetUserWeatherUseCase
import com.mahmoud.weatherapp.modules.weather.domain.model.entity.WeatherEntity
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
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
    private val getUserWeatherUseCase: GetUserWeatherUseCase,
    private val getCityWeatherUseCase: GetCityWeatherUseCase
) : BaseViewModel<WeatherUIState, WeatherEffects, WeatherEvents>(
    mainDispatcher,
    WeatherUIState()
) {

    override fun transform(event: WeatherEvents) {
        when (event) {
            is WeatherEvents.GetCityWeather -> getCityWeather(event.city)
            is WeatherEvents.RetryCitySearchWeather -> getCityWeather(currentState.query)
            is WeatherEvents.GetUserWeather -> getUserWeather()
        }
    }

    private fun getCityWeather(city: String) =
        launchBlock(onStart = { onGetCityWeather(city) }, onError = { it.doOnError(true) }) {
            getCityWeatherUseCase.invoke(city).collectLatest { data ->
                doOnGetWeatherDataSuccess(data)
            }
        }

    private fun onGetCityWeather(city: String) =
        setState {
            copy(
                loading = true,
                error = null,
                query = city
            )
        }

    private fun getUserWeather() =
        launchBlock(
            onStart = { setState { copy(loading = true, error = null) } },
            onError = { it.doOnError(false) }) {
            getUserWeatherUseCase(Unit).collectLatest { data ->
                doOnGetWeatherDataSuccess(data)
            }
        }

    private fun doOnGetWeatherDataSuccess(data: WeatherEntity) =
        setState {
            copy(
                loading = false,
                weather = data.toUI(),
            )
        }


    private fun Throwable.doOnError(isCitySource: Boolean) =
        if (currentState.weather != null)
            handleErrorEffect()
        else
            handleErrorState(isCitySource)

    private fun Throwable.handleErrorEffect() {
        setEffect { WeatherEffects.ShowWeatherError(handleError()) }
        setState { copy(loading = false) }
    }

    private fun Throwable.handleErrorState(isCitySource: Boolean) =
        setState {
            copy(
                loading = false,
                error = handleError(),
                isRetrySourceCitySearch = isCitySource
            )
        }

    private fun Throwable.handleError(): Int =
        when (this) {
            is FailedToGetLocationException -> R.string.msg_failed_to_get_user_location
            is EmptySearchCityException -> R.string.msg_city_field_required
            is InvalidSearchCityLengthException -> R.string.msg_search_city_length_validation
            is InvalidateQuerySearchException -> R.string.msg_invalidate_query_search
            is InvalidApiKeyException -> R.string.msg_invalidate_query_search
            is CityNotFountException -> R.string.msg_no_city_found
            else -> R.string.msg_something_went_wrong
        }
}
