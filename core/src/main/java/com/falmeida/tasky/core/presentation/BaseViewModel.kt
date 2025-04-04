package com.falmeida.tasky.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ScreenState, A : Action, E : Effect> : ViewModel() {

    private val initialScreenState: S by lazy { createInitialScreenState() }
    protected abstract fun createInitialScreenState(): S

    private val _screenState: MutableStateFlow<S> = MutableStateFlow(initialScreenState)
    protected open fun setupInitialAction(): A? = null

    /**
     * Exposes `screenState` and triggers an initial action when it starts being collected.
     */
    val screenState = _screenState
        .onStart {
            setupInitialAction()?.let { sendAction(it) }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialScreenState
        )

    private val _actions: MutableSharedFlow<A> = MutableSharedFlow()

    private val _effect: Channel<E> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            _actions.collect {
                handleActions(it)
            }
        }
    }

    protected abstract suspend fun handleActions(action: A)

    protected fun updateScreenState(reduce: S.() -> S) {
        _screenState.value = screenState.value.reduce()
    }

    fun sendAction(action: A) = viewModelScope.launch { _actions.emit(action) }

    protected fun sendEffect(effect: E) = viewModelScope.launch { _effect.send(effect) }
}