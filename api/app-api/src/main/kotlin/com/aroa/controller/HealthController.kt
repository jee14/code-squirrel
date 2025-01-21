package com.aroa.controller

import com.aroa.support.response.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/ping")
    fun healthCheck(): ApiResponse<String> = ApiResponse.success("pong")
}
