package com.falmeida.tasky.core.network.auth.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    @SerialName("token")
    val accessToken: String,

    @SerialName("refreshToken")
    val refreshToken: String
)
