package com.falmeida.tasky.core.domain.model

import java.time.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val dateTime: LocalDateTime,
    val remindAt: LocalDateTime,
    val isDone: Boolean
)

data class Reminder(
    val id: String,
    val title: String,
    val description: String?,
    val dateTime: LocalDateTime,
    val remindAt: LocalDateTime
)