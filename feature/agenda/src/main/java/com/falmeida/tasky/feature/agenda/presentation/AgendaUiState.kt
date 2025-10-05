package com.falmeida.tasky.feature.agenda.presentation

import java.time.LocalDate

data class AgendaUiState(
    val date: LocalDate = LocalDate.now(),
    val isLoading: Boolean = false,
    val events: List<EventUi> = emptyList(),
    val tasks: List<TaskUi> = emptyList(),
    val reminders: List<ReminderUi> = emptyList(),
    val error: String? = null
)

// Minimal UI models to keep UI-layer decoupled from domain
data class EventUi(
    val id: String,
    val title: String,
    val timeLabel: String
)

data class TaskUi(
    val id: String,
    val title: String,
    val timeLabel: String,
    val isDone: Boolean
)

data class ReminderUi(
    val id: String,
    val title: String,
    val timeLabel: String
)

