package com.falmeida.tasky.core.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    fun ui(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}