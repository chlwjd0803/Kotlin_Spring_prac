package com.example.kt_post.domain.user.controller

import com.example.kt_post.domain.user.dto.CreateUserDto
import com.example.kt_post.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/users")
class UserController(private val userService : UserService) {

    @GetMapping
    fun getAllUsers() =
        ResponseEntity.ok().body(userService.getAllUsers())

    @PostMapping
    fun createUser(@RequestBody dto : CreateUserDto) =
        ResponseEntity.ok().body(userService.createUser(dto))

}
