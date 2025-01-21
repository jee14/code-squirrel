package com.aroa.review.service

import com.aroa.review.model.Code
import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val chatClient: ChatClient,
) {
    @Value("classpath:prompts/review/system-prompt.st")
    private lateinit var review: Resource

    fun review(code: Code): String {
        return chatClient.prompt()
            .system(review)
            .user(code.content)
            .call()
            .content()
            .toString()
    }
}
