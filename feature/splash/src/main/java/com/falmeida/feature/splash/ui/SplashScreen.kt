package com.falmeida.feature.splash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.designsystem.component.TaskyLogo


@Composable
fun AdDetailScreenWrapper(viewModel: SplashViewModel = hiltViewModel()) {
    val screenState by viewModel.screenState.collectAsState()

    SplashScreen(screenState = screenState.dataState)
}

@Composable
fun SplashScreen(
    screenState: TaskyResult<Nothing>,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        TaskyLogo()
    }
}