package com.falmeida.tasky.core.network.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenRequest(
    val refreshToken: String
)
