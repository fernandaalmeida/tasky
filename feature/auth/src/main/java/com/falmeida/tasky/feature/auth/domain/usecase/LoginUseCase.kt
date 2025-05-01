package com.falmeida.tasky.feature.auth.domain.usecase

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.feature.auth.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: IAuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): TaskyResult<AuthResponse> {
        return repository.login(loginRequest)
    }
}
