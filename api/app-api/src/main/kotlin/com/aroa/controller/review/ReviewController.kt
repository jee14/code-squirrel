package com.aroa.controller.review

import com.aroa.controller.review.request.ReviewRequest
import com.aroa.controller.review.response.ReviewResponse
import com.aroa.review.service.ReviewService
import com.aroa.support.response.ApiResponse
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
        @RequestBody request: ReviewRequest,
    ): ApiResponse<ReviewResponse> {
        val message = reviewService.review(request.toCode())
        return ApiResponse.success(ReviewResponse(message))
    }
}
