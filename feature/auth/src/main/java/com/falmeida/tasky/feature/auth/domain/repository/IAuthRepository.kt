package com.falmeida.tasky.feature.auth.domain.repository

import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.RegisterRequest

/**
 * Repository interface for authentication operations.
 */
interface IAuthRepository {
    /**
     * Logs in a user with the provided email and password.
     */
    suspend fun login(email: String, password: String): TaskyResult<AuthResponse>

    /**
     * Registers a new user with the provided request.
     */
    suspend fun  register(request: RegisterRequest): TaskyResult<Unit>
}
