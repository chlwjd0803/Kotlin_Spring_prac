package com.example.kt_post.domain.user.repository

import com.example.kt_post.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {
//    fun findById(id: Long) : Optional<User>
}