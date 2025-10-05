package com.falmeida.tasky.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: String,
    val title: String,
    val description: String? = null,
    val time: Long,
    val remindAt: Long? = null,
    val isDone: Boolean = false
)

