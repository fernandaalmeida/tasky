package com.falmeida.tasky.core.network.auth

import com.falmeida.tasky.core.model.AuthResponse
import com.falmeida.tasky.core.model.LoginRequest
import com.falmeida.tasky.core.model.RegisterRequest
import com.falmeida.tasky.core.network.auth.model.RefreshTokenRequest
import com.falmeida.tasky.core.network.auth.model.RefreshTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<Unit>

    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequest
    ): Response<RefreshTokenResponse>
}
