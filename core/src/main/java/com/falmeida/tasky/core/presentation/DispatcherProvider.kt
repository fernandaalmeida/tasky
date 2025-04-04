package com.falmeida.tasky.core.presentation

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() {
    fun ui(): CoroutineDispatcher = Dispatchers.Main
    fun io(): CoroutineDispatcher = Dispatchers.IO
}
