
package com.falmeida.tasky.core.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)

@Serializable
data class AuthResponse(
    val token: String
)
