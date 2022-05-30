package com.mahmoud.weatherapp.modules.weather.presentation.view

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.mahmoud.weatherapp.base.BaseActivity
import com.mahmoud.weatherapp.core.extentions.showAlerterError
import com.mahmoud.weatherapp.databinding.ActivityMainBinding
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherDataUIState
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherEffects
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherEvents
import com.mahmoud.weatherapp.modules.weather.presentation.model.WeatherUIState
import com.mahmoud.weatherapp.modules.weather.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<WeatherUIState, WeatherEffects, WeatherEvents, ActivityMainBinding, WeatherViewModel>() {

    override val viewModel: WeatherViewModel by viewModels()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                getUserWeather()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                getUserWeather()
            }
            else -> {
                // No location access granted.
            }
        }
    }

    override fun bindView(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun setup() {
        requestLocationPermission()
        initActions()
    }

    private fun initActions() {
        binding.viewPromoCodeInput.tvSearch.setOnClickListener {
            viewModel.setEvent(WeatherEvents.GetCityWeather(binding.viewPromoCodeInput.etCity.text.toString()))
        }
    }

    override fun renderState(state: WeatherUIState): Unit = with(state) {
        binding.layoutStateView.showLoading(loading)
        binding.layoutStateView.setStateViewBackgroundColor(getStateViewBackgroundColor())
        error?.let {
            binding.layoutStateView.showError(
                getString(it),
                retry = { handleRetryAction(isRetrySourceCitySearch) })
        }
        binding.viewPromoCodeInput.tvSearch.isEnabled = !loading
        weather?.renderWeatherData()
    }


    private fun WeatherDataUIState.renderWeatherData() {
        binding.viewWeatherStatus.apply {
            viewStatusCity.setValue(cityName)
            viewStatusLat.setValue(latitude)
            viewStatusLon.setValue(longitude)
            viewStatusWeather.setValue(weather)
            viewStatusWeatherDescription.setValue(weatherDescription)
            viewStatusTemperature.setValue(temperature)
            viewStatusMinimumTemperature.setValue(minimumTemperature)
            viewStatusMaximumTemperature.setValue(maximumTemperature)
            viewStatusAtmosphericPressure.setValue(atmosphericPressure)
            viewStatusHumidityPercentage.setValue(humidityPercentage)
            viewStatusVisibility.setValue(visibility)
            viewStatusWindSpeed.setValue(windSpeed)
            viewStatusWindDirection.setValue(windDirection)
            viewStatusCloudsPercentage.setValue(cloudsPercentage)
            viewStatusSunriseTime.setValue(sunriseTime)
            viewStatusSunsetTime.setValue(sunsetTime)

            viewStatusWeather.isVisible = showWeather
            viewStatusWeatherDescription.isVisible = showWeather
        }
    }

    override fun renderEffect(effect: WeatherEffects) {
        when (effect) {
            is WeatherEffects.ShowWeatherError -> showAlerterError(errorMessage = getString(effect.message))
        }
    }


    private fun handleRetryAction(isRetrySourceCitySearch: Boolean) {
        if (isRetrySourceCitySearch)
            viewModel.setEvent(WeatherEvents.RetryCitySearchWeather)
        else
            requestLocationPermission()
    }

    private fun WeatherUIState.getStateViewBackgroundColor() =
        if (weather == null)
            android.R.color.white
        else
            android.R.color.transparent


    private fun requestLocationPermission() = locationPermissionRequest.launch(
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    private fun getUserWeather() =
        viewModel.setEvent(WeatherEvents.GetUserWeather)
}
