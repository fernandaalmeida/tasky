package com.falmeida.tasky.core.data.di

import com.falmeida.tasky.core.data.remote.api.TaskyService
import com.falmeida.tasky.core.data.repository.AgendaRepositoryImpl
import com.falmeida.tasky.core.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAgendaRepository(service: TaskyService): AgendaRepository {
        return AgendaRepositoryImpl(service)
    }
}

