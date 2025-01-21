package com.aroa.controller

import com.aroa.review.service.ReviewService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/reviews")
@RestController
class ReviewController(
    private val reviewService: ReviewService,
) {
    @PostMapping
    fun review(
        @RequestBody code: String,
    ): String {
        return reviewService.review(code)
    }
}