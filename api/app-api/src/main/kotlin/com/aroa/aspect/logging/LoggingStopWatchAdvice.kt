package com.aroa.aspect.logging

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Aspect
@Component
class LoggingStopWatchAdvice {
    companion object {
        val log: Logger = LoggerFactory.getLogger(this::class.java)
        const val MAX_AFFORDABLE_TIME: Long = 5000
    }

    @Around("execution(* com.aroa.controller..*Controller.*(..))")
    fun stopWatchTarget(joinPoint: ProceedingJoinPoint): Any? {
        val startAt = System.currentTimeMillis()
        val proceed = joinPoint.proceed()
        val endAt = System.currentTimeMillis()
        val timeMs = (endAt - startAt)

        val className = joinPoint.signature.declaringType.simpleName
        val methodName = joinPoint.signature.name

        if (timeMs > MAX_AFFORDABLE_TIME) {
            log.warn(
                "method=${getMethod()}, url=${getRequestURI()}, call: $className - $methodName - timeMs:${timeMs}ms",
            )
            return proceed
        }

        log.info(
            "method=${getMethod()}, url=${getRequestURI()}, call: $className - $methodName - timeMs:${timeMs}ms",
        )
        return proceed
    }

    private fun getMethod(): String {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        return request.method
    }

    private fun getRequestURI(): String {
        val request = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
        val sb = StringBuilder(128)

        sb.append(request.requestURL)
        if (null != request.queryString) {
            sb.append("?")
            sb.append(request.queryString)
        }
        return sb.toString()
    }
}
