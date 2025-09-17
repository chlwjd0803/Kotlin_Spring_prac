package com.example.kt_post.domain.post.repository

import com.example.kt_post.domain.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findByUser_Id(userId : Long) : List<Post>
}