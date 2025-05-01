package com.falmeida.tasky.core.network.auth

interface TokenManager {
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun saveTokens(accessToken: String, refreshToken: String)
    suspend fun clearTokens()
    suspend fun refreshAccessToken(): Boolean
}
