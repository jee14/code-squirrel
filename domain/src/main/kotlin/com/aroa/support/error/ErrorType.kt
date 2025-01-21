package com.aroa.support.error

enum class ErrorType(
    val kind: ErrorKind,
    val code: ErrorCode,
    val message: String,
    val level: ErrorLevel,
) {
    /** Common */
    DEFAULT(ErrorKind.SERVER_ERROR, ErrorCode.C500, "에러가 발생했습니다.", ErrorLevel.ERROR),
    INVALID_REQUEST(ErrorKind.CLIENT_ERROR, ErrorCode.C400, "잘못된 요청입니다.", ErrorLevel.WARN),
}
