package com.mahmoud.weatherapp.modules.weather.presentation.view

import androidx.activity.viewModels
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

    override fun setup() {

    }

    override fun bindView(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun renderState(state: WeatherUIState) {

    }

    override fun renderEffect(effect: WeatherEffects) {
        when (effect) {
            is WeatherEffects.ShowWeatherError -> showAlerterError(errorMessage = getString(effect.message))
        }
    }
}