package com.falmeida.tasky.feature.auth.di

import com.falmeida.tasky.feature.auth.domain.validator.AuthValidator
import com.falmeida.tasky.feature.auth.domain.validator.IAuthValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthValidatorModule {

    @Binds
    abstract fun bindAuthValidator(
        impl: AuthValidator
    ): IAuthValidator
}