package com.falmeida.tasky.core.presentation

import kotlinx.coroutines.CoroutineDispatcher

interface IDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}