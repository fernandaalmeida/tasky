package com.falmeida.tasky.core.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

interface Effect
@Composable
fun <T : Effect> HandleEffects(
    effectFlow: Flow<T>,
    key: Any? = Unit,
    content: suspend CoroutineScope.(effect: T) -> Unit // Use suspend for better async handling
) {
    LaunchedEffect(key1 = key) {
        try {
            effectFlow.collectLatest { effect ->
                content(effect)  // Collect effects
            }
        } catch (e: Exception) {
            e.printStackTrace() // Handle exceptions (if any)
        }
    }
}