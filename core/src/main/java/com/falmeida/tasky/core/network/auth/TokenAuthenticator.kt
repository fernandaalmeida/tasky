package com.falmeida.tasky.core.network.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) {
            return null
        }

        val tokenRefreshed = runCatching {
            runBlocking {
                tokenManager.refreshAccessToken()
            }
        }.getOrDefault(false)


        if (!tokenRefreshed) {
            return null
        }

        val newAccessToken = runCatching {
            runBlocking {
                tokenManager.getAccessToken()
            }
        }.getOrNull() ?: return null


        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }

    private fun responseCount(response: Response): Int {
        var result = 1
        var priorResponse = response.priorResponse
        while (priorResponse != null) {
            result++
            priorResponse = priorResponse.priorResponse
        }
        return result
    }
}
