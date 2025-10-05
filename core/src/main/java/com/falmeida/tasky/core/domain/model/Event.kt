package com.falmeida.tasky.core.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val date: LocalDate,
    val time: LocalTime,
    val type: EventType
)

enum class EventType { PROJECT, MEETING, BREAK }