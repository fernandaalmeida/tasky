package com.falmeida.tasky.feature.auth.ui.login

import androidx.compose.runtime.Composable


@Composable
fun LoginScreenWrapper(
    onNavigateToRegister: () -> Unit
) {
    LoginScreen(
        onNavigateToRegister = onNavigateToRegister
    )
}

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit = {}
) {

}