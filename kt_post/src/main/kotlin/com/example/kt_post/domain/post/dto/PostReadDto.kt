package com.example.kt_post.domain.post.dto

data class PostReadDto(
    var userId: Long,
    var nickname: String,
    var postId: Long,
    var title: String,
    var content: String,
)