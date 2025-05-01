package com.falmeida.tasky.core.network.util

import com.falmeida.tasky.core.domain.TaskyResult

import retrofit2.Response

suspend fun <T> safeApiResponse(
    apiCall: suspend () -> Response<T>
): TaskyResult<T> =
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                TaskyResult.Success(it)
            } ?: TaskyResult.Error("Empty response body")
        } else {
            TaskyResult.Error("HTTP ${response.code()}: ${response.message()}")
        }
    } catch (e: Exception) {
        TaskyResult.Error(e.message ?: "Unknown error")
    }

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): TaskyResult<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                TaskyResult.Success(body)
            } else {
                TaskyResult.Error("Empty response body")
            }
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            TaskyResult.Error(errorMessage)
        }
    } catch (e: Exception) {
        TaskyResult.Error(e.message ?: "Unknown error")
    }
}
