package com.falmeida.tasky.feature.auth.ui.register

import com.falmeida.tasky.core.presentation.Action

sealed interface RegisterAction : Action {
    data class EnteredName(val value: String) : RegisterAction
    data class EnteredEmail(val value: String) : RegisterAction
    data class EnteredPassword(val value: String) : RegisterAction
    data object TogglePasswordVisibility : RegisterAction
    data object Submit : RegisterAction
    data object NavigateToLogin : RegisterAction
}

sealed interface RegisterEffect {
    data object NavigateToLogin : RegisterEffect
}

data class RegisterUiState(
    val name: InputFieldState = InputFieldState(),
    val email: InputFieldState = InputFieldState(),
    val password: PasswordFieldState = PasswordFieldState()
)

data class InputFieldState(
    val text: String = "",
    val isValid: Boolean = false
)

data class PasswordFieldState(
    val text: String = "",
    val isVisible: Boolean = false,
    val isValid: Boolean = false
)