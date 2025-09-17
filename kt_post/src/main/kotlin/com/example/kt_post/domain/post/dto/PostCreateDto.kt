package com.example.kt_post.domain.post.dto

data class PostCreateDto(
    var userId: Long,
    var title: String,
    var content: String,
)
