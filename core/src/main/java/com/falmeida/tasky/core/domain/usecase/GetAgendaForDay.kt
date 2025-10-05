package com.falmeida.tasky.core.domain.usecase

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.domain.model.AgendaDay
import com.falmeida.tasky.core.domain.repository.AgendaRepository
import java.time.LocalDate
import javax.inject.Inject

class GetAgendaForDay @Inject constructor(
    private val repository: AgendaRepository
) {
    suspend operator fun invoke(date: LocalDate): TaskyResult<AgendaDay> = repository.getAgendaForDay(date)
}
