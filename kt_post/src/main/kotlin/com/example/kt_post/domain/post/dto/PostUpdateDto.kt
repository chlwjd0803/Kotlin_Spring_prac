package com.example.kt_post.domain.post.dto

data class PostUpdateDto(
    var userId : Long,
    var title : String?,
    var content : String?
)