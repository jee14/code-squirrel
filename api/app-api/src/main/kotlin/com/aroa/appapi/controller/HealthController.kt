package com.aroa.appapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/ping")
    fun healthCheck(): String = "pong"
}
