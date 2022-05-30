package com.mahmoud.weatherapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmoud.weatherapp.core.utils.Action
import com.mahmoud.weatherapp.core.utils.Consumer
import com.mahmoud.weatherapp.core.utils.Producer
import com.mahmoud.weatherapp.core.utils.Reducer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, EFFECT, EVENT>(
    private val dispatcher: CoroutineDispatcher,
    initialState: STATE
) : ViewModel() {

    fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                transform(it)
            }
        }
    }

    protected abstract fun transform(event: EVENT)

    private val _uiState: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    private val event = _event.asSharedFlow()

    private val _effect: MutableSharedFlow<EFFECT> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    // Get Current State
    protected val currentState: STATE
        get() = uiState.value

    fun setEvent(event: EVENT) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }

    protected fun setState(reduce: Reducer<STATE>) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEffect(builder: Producer<EFFECT>) {
        val effectValue = builder()
        viewModelScope.launch { _effect.emit(effectValue) }
    }

    fun launchBlock(
        onStart: Action? = null,
        onError: Consumer<Throwable>? = null,
        block: suspend CoroutineScope.() -> Unit
    ) {
        onStart?.invoke()
        viewModelScope.launch(dispatcher + coroutineExceptionHandler(onError)) {
            block.invoke(this)
        }
    }

    private fun coroutineExceptionHandler(onError: Consumer<Throwable>?) =
        CoroutineExceptionHandler { _, throwable ->
            onError?.invoke(throwable)
        }
}
