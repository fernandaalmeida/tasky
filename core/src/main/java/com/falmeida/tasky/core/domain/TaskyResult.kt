package com.falmeida.tasky.core.domain

sealed class TaskyResult<out T> {
    data class Success<out T>(val data: T) : TaskyResult<T>()
    data class Error(val message: String) : TaskyResult<Nothing>()
    data object Loading : TaskyResult<Nothing>()
}

fun <T> T.toTaskyResult(): TaskyResult<T> {
    return TaskyResult.Success(this)
}

fun <T> String.toTaskyError(): TaskyResult<T> {
    return TaskyResult.Error(this)
}

fun <T> TaskyResult<T>.isSuccess(): Boolean {
    return this is TaskyResult.Success
}

fun <T> TaskyResult<T>.isFailure(): Boolean {
    return this is TaskyResult.Error
}

fun <T> TaskyResult<T>.getData(): T {
    return (this as TaskyResult.Success).data
}

fun <T> TaskyResult<T>.getError(): String {
    return (this as TaskyResult.Error).message
}

fun <T> TaskyResult<T>.getOrElse(onFailure: (String) -> T): T = when (this) {
    is TaskyResult.Success -> data
    is TaskyResult.Error -> onFailure(message)
    is TaskyResult.Loading -> throw IllegalStateException("No value available in Loading state")
}

fun <T> TaskyResult<T>.getOrNull(): T? = when (this) {
    is TaskyResult.Success -> data
    else -> null
}

inline fun <R, T> TaskyResult<T>.fold(
    onSuccess: (T) -> R,
    onFailure: (String) -> R,
): R = when (this) {
    is TaskyResult.Success -> onSuccess(data)
    is TaskyResult.Error -> onFailure(message)
    is TaskyResult.Loading -> throw IllegalStateException("No value available in Loading state")
}

inline fun <R, T> TaskyResult<T>.onSuccessTransform(
    transform: (T) -> R,
): TaskyResult<R> = when (this) {
    is TaskyResult.Success -> TaskyResult.Success(transform(data))
    is TaskyResult.Error -> TaskyResult.Error(message)
    is TaskyResult.Loading -> TaskyResult.Loading
}

inline fun <R, T> TaskyResult<T>.onSuccessTransformToResult(
    transform: (T) -> TaskyResult<R>,
): TaskyResult<R> = when (this) {
    is TaskyResult.Success -> transform(data)
    is TaskyResult.Error -> TaskyResult.Error(message)
    is TaskyResult.Loading -> TaskyResult.Loading
}

inline fun <T> TaskyResult<T>.onSuccess(
    block: (T) -> Unit
): TaskyResult<T> {
    if (this is TaskyResult.Success) block(data)
    return this
}

inline fun <T> TaskyResult<T>.onError(
    block: (String) -> Unit
): TaskyResult<T> {
    if (this is TaskyResult.Error) block(message)
    return this
}

inline fun <T> TaskyResult<T>.onComplete(
    block: () -> Unit
): TaskyResult<T> {
    block()
    return this
}
