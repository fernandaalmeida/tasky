package com.falmeida.tasky.feature.auth.ui.register

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
fun RegisterScreenWrapper(
    viewModel: RegisterViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit = {},
    onShowError: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    ObserveAsEvents(flow = viewModel.effect) { effect ->
        when (effect) {
            is RegisterEffect.NavigateToLogin -> onNavigateToLogin()
            is RegisterEffect.NavigateToHome -> onNavigateToLogin()
            is RegisterEffect.ShowError -> onShowError(effect.message)
        }
    }
    RegisterScreen(
        state = state,
        onEvent = viewModel::onAction
    )
}

@Composable
fun RegisterScreen(
    state: RegisterUiState,
    onEvent: (RegisterAction) -> Unit
) {
    AuthScreenLayout(
        title = "Create your account",
        onBottomTextClick = { onEvent(RegisterAction.NavigateToLogin) },
        bottomText = "ALREADY HAVE AN ACCOUNT?",
        clickableText = "LOG IN",
        buttonText = "GET STARTED",
        onButtonClick = { onEvent(RegisterAction.Submit) },
        buttonEnabled = state.name.isValid && state.email.isValid && state.password.isValid

    ) {
        InputField(
            label = "Name",
            value = state.name.text,
            onValueChange = { onEvent(RegisterAction.EnteredName(it)) },
            isValid = state.name.isValid
        )

        Spacer(modifier = Modifier.height(16.dp))
        InputField(
            label = "Email address",
            value = state.email.text,
            onValueChange = { onEvent(RegisterAction.EnteredEmail(it)) },
            isValid = state.email.isValid,
            keyboardType = KeyboardType.Email,

            )

        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(
            value = state.password.text,
            onValueChange = { onEvent(RegisterAction.EnteredPassword(it)) },
            isVisible = state.password.isVisible,
            onVisibilityToggle = { onEvent(RegisterAction.TogglePasswordVisibility) }
        )
    }
}
