package com.falmeida.feature.splash.ui

import com.falmeida.tasky.core.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashScreenState, SplashAction, SplashEffect>() {

    override fun createInitialScreenState(): SplashScreenState {
        return SplashScreenState()
    }

    public override suspend fun handleActions(action: SplashAction) {
    }

}