package com.falmeida.tasky.feature.auth.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.core.presentation.ActionHandler
import com.falmeida.tasky.feature.auth.domain.usecase.RegisterUseCase
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validator: IAuthValidator,
    private val registerUseCase: RegisterUseCase
) : ViewModel(), ActionHandler<RegisterAction> {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    override fun onAction(event: RegisterAction) {
        when (event) {
            is RegisterAction.EnteredName -> handleEnteredName(event.value)
            is RegisterAction.EnteredEmail -> handleEnteredEmail(event.value)
            is RegisterAction.EnteredPassword -> handleEnteredPassword(event.value)
            RegisterAction.TogglePasswordVisibility -> handleTogglePasswordVisibility()
            RegisterAction.Submit -> handleSubmit()
            RegisterAction.NavigateToLogin -> navigateToLogin()
        }
    }

    private fun handleEnteredName(name: String) {
        val isValid = validator.isValidName(name)
        _uiState.update {
            it.copy(
                name = InputFieldState(
                    text = name,
                    isValid = isValid
                )
            )
        }
    }

    private fun handleEnteredEmail(email: String) {
        val isValid = validator.isValidEmail(email)
        _uiState.update {
            it.copy(
                email = InputFieldState(
                    text = email,
                    isValid = isValid
                )
            )
        }
    }

    private fun handleEnteredPassword(password: String) {
        val isValid = validator.isValidPassword(password)
        _uiState.update {
            it.copy(
                password = it.password.copy(
                    text = password,
                    isValid = isValid
                )
            )
        }
    }

    private fun handleTogglePasswordVisibility() {
        _uiState.update {
            it.copy(password = it.password.copy(isVisible = !it.password.isVisible))
        }
    }
    private fun handleSubmit() {
        val currentState = uiState.value

        if (!currentState.isFormValid) {
            viewModelScope.launch {
                _effect.send(RegisterEffect.ShowError("Please fill in all fields correctly."))
            }
            return
        }

        val request = RegisterRequest(
            fullName = currentState.name.text.trim(),
            email = currentState.email.text.trim(),
            password = currentState.password.text
        )

        viewModelScope.launch {
            when (val result = registerUseCase(request)) {
                is TaskyResult.Success -> _effect.send(RegisterEffect.NavigateToHome)
                is TaskyResult.Error -> _effect.send(RegisterEffect.ShowError(result.message))
                TaskyResult.Loading -> {}
            }
        }
    }

    private fun navigateToLogin() {
        viewModelScope.launch {
            _effect.send(RegisterEffect.NavigateToLogin)
        }
    }
}
