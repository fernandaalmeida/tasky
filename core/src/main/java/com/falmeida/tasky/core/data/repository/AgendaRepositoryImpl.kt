package com.falmeida.tasky.core.data.repository

import com.falmeida.tasky.core.data.mapper.mapAgendaDayDtoToDomain
import com.falmeida.tasky.core.data.remote.api.TaskyService
import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.domain.model.AgendaDay
import com.falmeida.tasky.core.domain.repository.AgendaRepository
import com.falmeida.tasky.core.network.util.safeApiCall
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val service: TaskyService
) : AgendaRepository {

    override suspend fun getAgendaForDay(date: LocalDate): TaskyResult<AgendaDay> {
        // Convert the given date at start of day to epoch millis in system zone
        val zone = ZoneId.systemDefault()
        val epochMillis = date.atStartOfDay(zone).toInstant().toEpochMilli()

        val result: TaskyResult<com.falmeida.tasky.core.data.remote.dto.AgendaDayDto> = safeApiCall { service.getAgenda(epochMillis) }

        return when (result) {
            is TaskyResult.Success -> {
                val dto = result.data
                TaskyResult.Success(mapAgendaDayDtoToDomain(dto, date))
            }
            is TaskyResult.Error -> TaskyResult.Error(result.message)
            TaskyResult.Loading -> TaskyResult.Loading
        }
    }
}
