package com.falmeida.tasky.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AgendaDayDto(
    val events: List<EventDto> = emptyList(),
    val tasks: List<TaskDto> = emptyList(),
    val reminders: List<TaskDto> = emptyList() // reuse TaskDto shape for reminder-like items
)

