package com.falmeida.tasky.core.data.mapper

import com.falmeida.tasky.core.data.remote.dto.AgendaDayDto
import com.falmeida.tasky.core.data.remote.dto.TaskDto
import com.falmeida.tasky.core.domain.model.AgendaDay
import com.falmeida.tasky.core.domain.model.Reminder
import com.falmeida.tasky.core.domain.model.Task
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun TaskDto.toDomain(): Task {
    val zone = ZoneId.systemDefault()
    val dateTime = Instant.ofEpochMilli(time).atZone(zone).toLocalDateTime()
    val remind = remindAt?.let { Instant.ofEpochMilli(it).atZone(zone).toLocalDateTime() } ?: dateTime

    return Task(
        id = id,
        title = title,
        description = description,
        dateTime = dateTime,
        remindAt = remind,
        isDone = isDone
    )
}

fun TaskDto.toReminderDomain(): Reminder {
    val zone = ZoneId.systemDefault()
    val dateTime = Instant.ofEpochMilli(time).atZone(zone).toLocalDateTime()
    val remind = remindAt?.let { Instant.ofEpochMilli(it).atZone(zone).toLocalDateTime() } ?: dateTime

    return Reminder(
        id = id,
        title = title,
        description = description,
        dateTime = dateTime,
        remindAt = remind
    )
}

// Explicit mapping function (safer to import and call from repository)
fun mapAgendaDayDtoToDomain(dto: AgendaDayDto, date: LocalDate): AgendaDay = AgendaDay(
    date = date,
    events = dto.events.map { it.toDomain() },
    tasks = dto.tasks.map { it.toDomain() },
    reminders = dto.reminders.map { it.toReminderDomain() }
)
