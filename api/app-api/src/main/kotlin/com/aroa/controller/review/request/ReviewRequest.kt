package com.aroa.controller.review.request

import com.aroa.review.model.Code

data class ReviewRequest(
    val code: String,
) {
    fun toCode(): Code {
        return Code(
            content = code,
        )
    }
}
