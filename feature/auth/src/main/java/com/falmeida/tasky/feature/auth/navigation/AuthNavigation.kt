package com.falmeida.tasky.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.falmeida.tasky.core.navigation.Screen
import com.falmeida.tasky.feature.auth.ui.login.LoginScreenWrapper
import com.falmeida.tasky.feature.auth.ui.register.RegisterScreenWrapper

fun NavGraphBuilder.authGraph(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    onShowError: (String) -> Unit, // ✅ Not @Composable
) {
    composable(Screen.Login.route) {
        LoginScreenWrapper(onNavigateToRegister = onNavigateToRegister)
    }

    composable(Screen.Register.route) {
        RegisterScreenWrapper(onNavigateToLogin = onNavigateToLogin, onShowError = onShowError)
    }
}