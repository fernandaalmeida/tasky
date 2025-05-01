package com.falmeida.tasky.feature.auth.ui.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.falmeida.tasky.core.presentation.ObserveAsEvents
import com.falmeida.tasky.feature.auth.ui.components.AuthScreenLayout
import com.falmeida.tasky.feature.auth.ui.register.components.InputField
import com.falmeida.tasky.feature.auth.ui.register.components.PasswordField


@Composable
fun LoginScreenWrapper(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigateToRegister: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onShowError: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            LoginEffect.NavigateToRegister -> onNavigateToRegister()
            LoginEffect.NavigateToHome -> onNavigateToHome()
            is LoginEffect.ShowError -> onShowError(effect.message)
        }
    }

    LoginScreen(
        state = state,
        onEvent = viewModel::onAction
    )
}

@Composable
fun LoginScreen(
    state: LoginUiState,
    onEvent: (LoginAction) -> Unit
) {
    AuthScreenLayout(
        title = "Welcome back",
        bottomText = "DON'T HAVE AN ACCOUNT?",
        clickableText = "REGISTER",
        buttonText = "LOG IN",
        onButtonClick = { onEvent(LoginAction.Submit) },
        onBottomTextClick = { onEvent(LoginAction.NavigateToRegister) },
        buttonEnabled = state.email.isValid && state.password.isValid
    ) {
        InputField(
            label = "Email address",
            value = state.email.text,
            onValueChange = { onEvent(LoginAction.EnteredEmail(it)) },
            isValid = state.email.isValid,
            keyboardType = KeyboardType.Email
        )
        Spacer(modifier = Modifier.height(16.dp))

        PasswordField(
            value = state.password.text,
            onValueChange = { onEvent(LoginAction.EnteredPassword(it)) },
            isVisible = state.password.isVisible,
            onVisibilityToggle = { onEvent(LoginAction.TogglePasswordVisibility) }
        )
    }
}
