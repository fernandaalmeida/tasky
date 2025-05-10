package com.falmeida.tasky.feature.auth.domain.usecase


import com.falmeida.tasky.core.domain.TaskyResult
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.feature.auth.data.repository.AuthRepository
import com.falmeida.tasky.feature.auth.domain.repository.IAuthRepository
import javax.inject.Inject

open class RegisterUseCase @Inject constructor(
    private val repository: IAuthRepository
) {
    suspend operator fun invoke(request: RegisterRequest): TaskyResult<Unit> {
        return repository.register(request)
    }
}
