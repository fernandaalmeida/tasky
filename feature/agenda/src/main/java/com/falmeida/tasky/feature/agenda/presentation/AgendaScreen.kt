package com.falmeida.tasky.feature.agenda.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.falmeida.tasky.core.presentation.ObserveAsEvents
import java.time.LocalDate

@Composable
fun AgendaScreenWrapper(
    viewModel: AgendaViewModel = hiltViewModel(),
    date: LocalDate = LocalDate.now(),
    onShowError: (String) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    ObserveAsEvents(viewModel.effect) { effect ->
        when (effect) {
            is AgendaEffect.ShowError -> onShowError(effect.message)
        }
    }

    // Trigger initial load
    androidx.compose.runtime.LaunchedEffect(date) {
        viewModel.onAction(AgendaAction.LoadDay(date))
    }

    AgendaScreen(state = state)
}

@Composable
fun AgendaScreen(state: AgendaUiState) {
    Surface(modifier = androidx.compose.ui.Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        if (state.isLoading) {
            Column {
                CircularProgressIndicator()
            }
            return@Surface
        }

        LazyColumn {
            item {
                Text(text = "Agenda for ${state.date}", style = MaterialTheme.typography.headlineSmall)
            }

            if (state.events.isNotEmpty()) {
                item { Text(text = "Events", style = MaterialTheme.typography.titleMedium) }
                items(state.events) { event ->
                    Column {
                        Text(event.title, style = MaterialTheme.typography.bodyLarge)
                        Text(event.timeLabel, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            if (state.tasks.isNotEmpty()) {
                item { Text(text = "Tasks", style = MaterialTheme.typography.titleMedium) }
                items(state.tasks) { task ->
                    Column {
                        Text(task.title, style = MaterialTheme.typography.bodyLarge)
                        Text(task.timeLabel, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            if (state.reminders.isNotEmpty()) {
                item { Text(text = "Reminders", style = MaterialTheme.typography.titleMedium) }
                items(state.reminders) { rem ->
                    Column {
                        Text(rem.title, style = MaterialTheme.typography.bodyLarge)
                        Text(rem.timeLabel, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }

            if (state.error != null) {
                item { Text(text = "Error: ${state.error}", color = MaterialTheme.colorScheme.error) }
            }
        }
    }
}

