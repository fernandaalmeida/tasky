package com.falmeida.tasky.core.domain

sealed class TaskyResult<out T> {
    data class Success<out T>(val data: T) : TaskyResult<T>()
    data class Error(val message: String) : TaskyResult<Nothing>()
    data object Loading : TaskyResult<Nothing>()
}