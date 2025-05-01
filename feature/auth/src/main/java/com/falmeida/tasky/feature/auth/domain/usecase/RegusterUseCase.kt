package com.falmeida.tasky.feature.auth.domain.usecase


import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.feature.auth.data.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): TaskyResult<Unit> {
        return repository.register(request)
    }
}
