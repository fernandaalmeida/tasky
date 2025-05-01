package com.falmeida.tasky.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("x-api-key", "UI3LtGm6Lj2pehRQ") // <-- put your real key
            .build()
        return chain.proceed(newRequest)
    }
}