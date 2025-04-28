package com.falmeida.tasky.feature.auth.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falmeida.tasky.core.presentation.ActionHandler
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validator: IAuthValidator
) : ViewModel(), ActionHandler<RegisterAction> {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>()
    val effect = _effect.receiveAsFlow()

    override fun onAction(event: RegisterAction) {
        when (event) {
            is RegisterAction.EnteredName -> {
                val isValid = validator.isValidName(event.value)
                _uiState.update {
                    it.copy(
                        name = InputFieldState(
                            text = event.value,
                            isValid = isValid
                        )
                    )
                }
            }

            is RegisterAction.EnteredEmail -> {
                val isValid = validator.isValidEmail(event.value)
                _uiState.update {
                    it.copy(
                        email = InputFieldState(
                            text = event.value,
                            isValid = isValid
                        )
                    )
                }
            }

            is RegisterAction.EnteredPassword -> {
                val isValid = validator.isValidPassword(event.value)
                _uiState.update {
                    it.copy(
                        password = it.password.copy(
                            text = event.value,
                            isValid = isValid
                        )
                    )
                }
            }

            RegisterAction.TogglePasswordVisibility -> {
                _uiState.update {
                    it.copy(password = it.password.copy(isVisible = !it.password.isVisible))
                }
            }

            RegisterAction.Submit -> {
            }

            RegisterAction.NavigateToLogin -> {
                viewModelScope.launch {
                    _effect.send(RegisterEffect.NavigateToLogin)
                }
            }
        }
    }
}
