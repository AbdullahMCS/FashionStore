package com.cyberwalker.fashionstore.util

sealed class AuthenticationResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : AuthenticationResult<T>(data)
    class Error<T>(message: String?) : AuthenticationResult<T>(message = message)
    class Loading<T> : AuthenticationResult<T>()
}