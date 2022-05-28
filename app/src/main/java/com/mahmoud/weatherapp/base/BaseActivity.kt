package com.mahmoud.weatherapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<STATE, EFFECT, EVENT, T : ViewBinding, VM : BaseViewModel<STATE, EFFECT, EVENT>>() : AppCompatActivity() {

    protected lateinit var binding: T

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding = bindView()

        setup()

        viewModel.subscribeEvents()

        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                renderState(it)
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.effect.collect {
                renderEffect(it)
            }
        }


    }

    abstract fun setup()
    protected abstract fun bindView(): T

    abstract fun renderState(state: STATE)
    abstract fun renderEffect(effect: EFFECT)
}