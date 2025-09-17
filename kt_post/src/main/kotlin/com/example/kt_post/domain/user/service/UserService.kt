package com.example.kt_post.domain.user.service

import com.example.kt_post.domain.user.dto.CreateUserDto
import com.example.kt_post.domain.user.dto.ReadUserDto
import com.example.kt_post.domain.user.entity.User
import com.example.kt_post.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {


    fun getAllUsers() = userRepository.findAll()

    // 여기는 getter setter가 따로 없는거같음
    fun createUser(dto : CreateUserDto) : ReadUserDto{
        val user : User = User(dto.nickname, dto.email)
        userRepository.save(user)
        return ReadUserDto(user.id, user.nickname, user.email)
    }

    fun deleteUser(userId : Long) : Unit {
        val user : User = userRepository.findById(userId).orElseThrow {
            IllegalArgumentException("해당 유저가 없음")
        }
        userRepository.delete(user)
    }
}
