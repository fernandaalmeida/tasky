package com.falmeida.tasky.core.network.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { tokenManager.getAccessToken() }
        val request = chain.request().newBuilder().apply {
            accessToken?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()
        return chain.proceed(request)
    }
}
