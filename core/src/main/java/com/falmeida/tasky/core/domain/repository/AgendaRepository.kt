package com.falmeida.tasky.core.domain.repository

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.domain.model.AgendaDay
import java.time.LocalDate

interface AgendaRepository {
    /**
     * Returns agenda items (events, tasks, reminders) for the given date wrapped in a TaskyResult.
     * Implementations should return TaskyResult.Success with an empty AgendaDay when no items exist.
     */
    suspend fun getAgendaForDay(date: LocalDate): TaskyResult<AgendaDay>
}
