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

sealed class RegisterEffect {
    data object NavigateToLogin : RegisterEffect()
    data object NavigateToHome : RegisterEffect()
    data class ShowError(val message: String) : RegisterEffect()
}

data class RegisterUiState(
    val name: InputFieldState = InputFieldState(),
    val email: InputFieldState = InputFieldState(),
    val password: PasswordFieldState = PasswordFieldState()
){
    val isFormValid: Boolean
        get() = name.isValid && email.isValid && password.isValid
}

data class InputFieldState(
    val text: String = "",
    val isValid: Boolean = false
)

data class PasswordFieldState(
    val text: String = "",
    val isVisible: Boolean = false,
    val isValid: Boolean = false
)