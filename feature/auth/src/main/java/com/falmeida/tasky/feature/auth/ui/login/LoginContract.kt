package com.falmeida.tasky.feature.auth.ui.login


import com.falmeida.tasky.core.presentation.Action
import com.falmeida.tasky.feature.auth.ui.register.InputFieldState
import com.falmeida.tasky.feature.auth.ui.register.PasswordFieldState

data class LoginUiState(
    val email: InputFieldState = InputFieldState(),
    val password: PasswordFieldState = PasswordFieldState()
) {
    val isFormValid: Boolean
        get() = email.isValid && password.isValid
}


sealed class LoginAction : Action  {
    data class EnteredEmail(val value: String) : LoginAction()
    data class EnteredPassword(val value: String) : LoginAction()
    data object TogglePasswordVisibility : LoginAction()
    data object Submit : LoginAction()
    data object NavigateToRegister : LoginAction()
}

sealed class LoginEffect {
    data object NavigateToHome : LoginEffect()
    data object NavigateToRegister : LoginEffect()
    data class ShowError(val message: String) : LoginEffect()
}