package com.falmeida.tasky.feature.agenda.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.domain.usecase.GetAgendaForDay
import com.falmeida.tasky.core.presentation.ActionHandler
import com.falmeida.tasky.feature.agenda.presentation.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

sealed interface AgendaAction {
    data class LoadDay(val date: LocalDate) : AgendaAction
}

sealed interface AgendaEffect {
    data class ShowError(val message: String) : AgendaEffect
}

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val getAgendaForDay: GetAgendaForDay
) : ViewModel(), ActionHandler<AgendaAction> {

    private val _uiState = MutableStateFlow(AgendaUiState())
    override val uiState: StateFlow<AgendaUiState> = _uiState

    private val _effect = Channel<AgendaEffect>()
    val effect = _effect.receiveAsFlow()

    override fun onAction(action: AgendaAction) {
        when (action) {
            is AgendaAction.LoadDay -> loadDay(action.date)
        }
    }

    private fun loadDay(date: LocalDate) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            when (val result = getAgendaForDay(date)) {
                is TaskyResult.Success -> {
                    _uiState.value = result.data.toUi().copy(isLoading = false)
                }
                is TaskyResult.Error -> {
                    val message = result.message
                    _uiState.value = _uiState.value.copy(isLoading = false, error = message)
                    _effect.send(AgendaEffect.ShowError(message))
                }
                TaskyResult.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }
}
