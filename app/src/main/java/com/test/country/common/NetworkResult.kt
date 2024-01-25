package com.test.country.common

sealed class NetworkResult<out T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Loading<out T> : NetworkResult<T>()
    class Success<out T>(data: T) : NetworkResult<T>(data)
    class Error<out T>(errorMessage: String) : NetworkResult<T>(errorMessage = errorMessage)
}