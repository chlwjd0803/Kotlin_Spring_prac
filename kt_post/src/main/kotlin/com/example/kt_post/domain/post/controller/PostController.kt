package com.example.kt_post.domain.post.controller

import com.example.kt_post.domain.post.dto.PostCreateDto
import com.example.kt_post.domain.post.entity.Post
import com.example.kt_post.domain.post.repository.PostRepository
import com.example.kt_post.domain.post.service.PostService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
@RequestMapping("/posts")
// 코틀린에선 생성자로 의존성 주입하는 방식이 클래스 옆에 바로 가능함
class PostController(private val postService : PostService) {

    @GetMapping
    fun getAll() = postService.findAll()

    @GetMapping("/user/{userId}")
    fun getByUserId(@PathVariable userId : Long) = postService.findByUserId(userId)

    @PostMapping
    fun createPost(@RequestBody dto : PostCreateDto) = postService.createPost(dto)
}
