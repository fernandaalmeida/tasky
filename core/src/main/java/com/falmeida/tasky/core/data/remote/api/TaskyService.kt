package com.falmeida.tasky.core.data.remote.api

import com.falmeida.tasky.core.data.remote.dto.AgendaDayDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TaskyService {

    @GET("agenda")
    suspend fun getAgenda(
        @Query("time") time: Long
    ): Response<AgendaDayDto>

    @GET("fullAgenda")
    suspend fun getFullAgenda(): Response<AgendaDayDto>
}
