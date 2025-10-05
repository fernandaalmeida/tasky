package com.falmeida.tasky.core.domain.model

import java.time.LocalDate

data class AgendaDay(
    val date: LocalDate,
    val events: List<Event>,
    val tasks: List<Task>,
    val reminders: List<Reminder>
)