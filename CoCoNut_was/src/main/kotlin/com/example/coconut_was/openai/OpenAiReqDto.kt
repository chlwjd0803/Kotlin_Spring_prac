package com.example.coconut_was.openai

data class OpenAiReqDto(
    val model: String,
    val messages: List<Message>
) {
    data class Message(
        val role: String,
        val content: String
    )
}
