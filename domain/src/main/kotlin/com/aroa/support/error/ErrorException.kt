package com.aroa.support.error

data class ErrorException(
    val errorType: ErrorType,
    val data: Any? = null,
) : RuntimeException(errorType.message)
