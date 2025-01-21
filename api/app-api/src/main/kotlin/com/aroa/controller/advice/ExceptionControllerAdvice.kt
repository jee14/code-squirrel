package com.aroa.controller.advice

import com.aroa.support.error.ErrorException
import com.aroa.support.error.ErrorKind
import com.aroa.support.error.ErrorLevel
import com.aroa.support.error.ErrorMessage
import com.aroa.support.error.ErrorType
import com.aroa.support.response.ApiResponse
import com.aroa.support.response.ResultType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionControllerAdvice : ResponseEntityExceptionHandler() {
    private val log: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(ErrorException::class)
    fun handleErrorException(e: ErrorException): ResponseEntity<ApiResponse<Any>> {
        when (e.errorType.level) {
            ErrorLevel.ERROR -> log.error("ErrorException: {}", e.message, e)
            ErrorLevel.WARN -> log.warn("ErrorException: {}", e.message, e)
            else -> log.info("ErrorException: {}", e.message, e)
        }

        val status =
            when (e.errorType.kind) {
                ErrorKind.CLIENT_ERROR -> HttpStatus.BAD_REQUEST
                else -> HttpStatus.INTERNAL_SERVER_ERROR
            }

        return ResponseEntity(ApiResponse.error(e.errorType, e.data), status)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Any>> {
        log.error("ErrorException: {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<ApiResponse<Any>> {
        log.warn("ErrorException: {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.INVALID_REQUEST, e.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ApiResponse<Any>> {
        log.warn("ErrorException: {}", e.message, e)
        return ResponseEntity(ApiResponse.error(ErrorType.DEFAULT, e.message), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleHttpMessageNotReadable(
        e: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        log.warn("ErrorException: {}", e.message, e)
        return ResponseEntity(
            error(ErrorType.INVALID_REQUEST),
            HttpStatus.BAD_REQUEST,
        )
    }

    private fun error(
        error: ErrorType,
        errorData: Any? = null,
    ): ApiResponse<*> {
        return ApiResponse(
            result = ResultType.ERROR,
            data = null,
            error = ErrorMessage(error, errorData),
        )
    }
}
