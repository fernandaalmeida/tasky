package com.falmeida.tasky.core.network.auth

import com.falmeida.tasky.core.network.auth.model.RefreshTokenRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultTokenManager @Inject constructor(
    private val authApi: AuthApi
) : TokenManager {

    private var accessToken: String? = null
    private var refreshToken: String? = null

    override suspend fun getAccessToken(): String = withContext(Dispatchers.IO) {
        accessToken.orEmpty()
    }

    override suspend fun getRefreshToken(): String = withContext(Dispatchers.IO) {
        refreshToken.orEmpty()
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String) {
        withContext(Dispatchers.IO) {
            this@DefaultTokenManager.accessToken = accessToken
            this@DefaultTokenManager.refreshToken = refreshToken
        }
    }

    override suspend fun clearTokens() {
        withContext(Dispatchers.IO) {
            accessToken = null
            refreshToken = null
        }
    }
    override suspend fun refreshAccessToken(): Boolean {
        return withContext(Dispatchers.IO) {
            val refresh = getRefreshToken()

            try {
                val response = authApi.refreshToken(RefreshTokenRequest(refresh))

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        saveTokens(
                            accessToken = body.accessToken,
                            refreshToken = body.refreshToken
                        )
                        true
                    } ?: false
                } else {
                    false
                }
            } catch (e: Exception) {
                false
            }
        }
    }

}
