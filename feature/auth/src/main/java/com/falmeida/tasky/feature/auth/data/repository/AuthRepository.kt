package com.falmeida.tasky.feature.auth.data.repository

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.core.network.auth.AuthApi
import com.falmeida.tasky.core.network.util.safeApiCall
import com.falmeida.tasky.feature.auth.domain.repository.IAuthRepository
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi
) : IAuthRepository {

    override suspend fun login(loginRequest: LoginRequest): TaskyResult<AuthResponse> =
        safeApiCall {
            api.login(loginRequest)
        }

    override suspend fun register(request: RegisterRequest): TaskyResult<Unit> = safeApiCall {
        api.register(request)
    }
}
