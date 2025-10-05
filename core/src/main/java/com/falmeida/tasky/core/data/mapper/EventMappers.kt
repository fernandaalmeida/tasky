package com.falmeida.tasky.core.data.mapper

import com.falmeida.tasky.core.data.remote.dto.EventDto
import com.falmeida.tasky.core.domain.model.Event
import com.falmeida.tasky.core.domain.model.EventType
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId

fun EventDto.toDomain(): Event {
    val zone = ZoneId.systemDefault()
    val instant = Instant.ofEpochMilli(from)
    val localDate = instant.atZone(zone).toLocalDate()
    val localTime = instant.atZone(zone).toLocalTime()

    // crude mapping; EventType isn't provided by API, default to MEETING
    val type = EventType.MEETING

    return Event(
        id = id,
        title = title,
        description = description.orEmpty(),
        date = localDate,
        time = localTime,
        type = type
    )
}

