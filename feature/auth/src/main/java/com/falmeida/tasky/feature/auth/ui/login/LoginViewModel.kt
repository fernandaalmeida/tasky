package com.falmeida.tasky.feature.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.core.presentation.ActionHandler
import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.feature.auth.domain.usecase.LoginUseCase
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import com.falmeida.tasky.feature.auth.ui.register.InputFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validator: IAuthValidator
) : ViewModel(), ActionHandler<LoginAction> {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _effect = Channel<LoginEffect>()
    val effect = _effect.receiveAsFlow()

    override fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EnteredEmail -> handleEmail(action.value)
            is LoginAction.EnteredPassword -> handlePassword(action.value)
            is LoginAction.TogglePasswordVisibility -> togglePasswordVisibility()
            LoginAction.Submit -> handleSubmit()
            LoginAction.NavigateToRegister -> navigateToRegister()
        }
    }

    private fun handleEmail(email: String) {
        _uiState.update {
            it.copy(
                email = InputFieldState(
                    text = email,
                    isValid = validator.isValidEmail(email)
                )
            )
        }
    }

    private fun handlePassword(password: String) {
        _uiState.update {
            it.copy(
                password = it.password.copy(
                    text = password,
                    isValid = validator.isValidPassword(password)
                )
            )
        }
    }

    private fun togglePasswordVisibility() {
        _uiState.update {
            it.copy(password = it.password.copy(isVisible = !it.password.isVisible))
        }
    }

    private fun handleSubmit() {
        val currentState = _uiState.value
        if (!currentState.isFormValid) return

        viewModelScope.launch {
            val request = LoginRequest(
                email = currentState.email.text,
                password = currentState.password.text
            )
            when (val result = loginUseCase(request)) {
                is TaskyResult.Success -> _effect.send(LoginEffect.NavigateToHome)
                is TaskyResult.Error -> _effect.send(LoginEffect.ShowError(result.message))
                TaskyResult.Loading -> Unit // No-op, or update state if needed
            }
        }
    }

    private fun navigateToRegister() {
        viewModelScope.launch {
            _effect.send(LoginEffect.NavigateToRegister)
        }
    }
}
