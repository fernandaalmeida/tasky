package com.falmeida.tasky.core.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Agenda : Screen("agenda")
}