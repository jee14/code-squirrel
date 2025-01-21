package com.aroa.support.response

import com.aroa.support.error.ErrorMessage
import com.aroa.support.error.ErrorType

data class ApiResponse<Data>(
    val result: ResultType,
    val data: Data? = null,
    val error: ErrorMessage? = null,
) {
    companion object {
        fun <Data> success(data: Data): ApiResponse<Data> {
            return ApiResponse(
                result = ResultType.SUCCESS,
                data = data,
                error = null,
            )
        }

        fun <Data> error(
            error: ErrorType,
            errorData: Any? = null,
        ): ApiResponse<Data> {
            return ApiResponse(
                result = ResultType.ERROR,
                data = null,
                error = ErrorMessage(error, errorData),
            )
        }
    }
}
