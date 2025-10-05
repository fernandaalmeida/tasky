package com.falmeida.tasky.core.data.remote.dto

import kotlinx.serialization.Serializable

/**
 * Partial DTO matching the Tasky API for events. Times are represented as epoch milliseconds.
 */
@Serializable
data class EventDto(
    val id: String,
    val title: String,
    val description: String? = null,
    val from: Long,
    val to: Long? = null,
    val remindAt: Long? = null
)

