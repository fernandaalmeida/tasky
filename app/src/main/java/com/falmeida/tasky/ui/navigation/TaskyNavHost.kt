package com.falmeida.tasky.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.falmeida.tasky.core.navigation.Screen
import com.falmeida.tasky.feature.auth.navigation.authGraph

@Composable
fun TaskyNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(
            onNavigateToLogin = { navController.navigate(Screen.Login.route) },
            onNavigateToRegister = { navController.navigate(Screen.Register.route) },
            onNavigateToHome = { navController.navigate(Screen.Register.route) },
            onShowError = { }
        )
    }
}