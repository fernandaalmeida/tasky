package com.falmeida.tasky.feature.auth.data.di

import com.falmeida.tasky.core.network.auth.AuthApi
import com.falmeida.tasky.feature.auth.data.repository.AuthRepository
import com.falmeida.tasky.feature.auth.domain.repository.IAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi): IAuthRepository {
        return AuthRepository(api)
    }
}
