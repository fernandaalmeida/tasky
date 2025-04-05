package com.falmeida.feature.splash.ui

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.presentation.Action
import com.falmeida.tasky.core.presentation.Effect
import com.falmeida.tasky.core.presentation.ScreenState

data class SplashScreenState(
    val dataState: TaskyResult<Nothing> = TaskyResult.Loading,
) : ScreenState

sealed class SplashAction : Action

sealed class SplashEffect : Effect