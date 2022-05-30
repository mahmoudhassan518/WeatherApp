package com.mahmoud.weatherapp.modules.weather.presentation.view

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.mahmoud.weatherapp.R
import com.mahmoud.weatherapp.base.BaseActivity
import com.mahmoud.weatherapp.core.extentions.showAlerterError
import com.mahmoud.weatherapp.databinding.ActivityMainBinding
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

    private fun getUserWeather() {
        viewModel.setEvent(WeatherEvents.GetUserWeather)
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

    override fun renderState(state: WeatherUIState) = with(state) {
        binding.layoutStateView.showLoading(
            loading,
            getStateViewBackgroundColor()
        )
        error?.let {
            binding.layoutStateView.showError(getString(it))
        }
    }

    private fun WeatherUIState.getStateViewBackgroundColor() =
        if (weather == null) android.R.color.white
        else R.color.colorTransparent

    override fun renderEffect(effect: WeatherEffects) {
        when (effect) {
            is WeatherEffects.ShowWeatherError -> showAlerterError(errorMessage = getString(effect.message))
        }
    }

    private fun requestLocationPermission() = locationPermissionRequest.launch(
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
}
