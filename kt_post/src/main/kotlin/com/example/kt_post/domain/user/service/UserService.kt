package com.example.kt_post.domain.user.service

import com.example.kt_post.domain.user.dto.CreateUserDto
import com.example.kt_post.domain.user.entity.User
import com.example.kt_post.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {


    fun getAllUsers() = userRepository.findAll()

    // 여기는 getter setter가 따로 없는거같음
    fun createUser(dto : CreateUserDto) =
        userRepository.save(User(dto.nickname, dto.email))
}
