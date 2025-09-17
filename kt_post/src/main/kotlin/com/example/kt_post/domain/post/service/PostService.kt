package com.example.kt_post.domain.post.service

import com.example.kt_post.domain.post.dto.PostCreateDto
import com.example.kt_post.domain.post.dto.PostReadDto
import com.example.kt_post.domain.post.entity.Post
import com.example.kt_post.domain.post.repository.PostRepository
import com.example.kt_post.domain.user.entity.User
import com.example.kt_post.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository

) {

    // 전체 조회 (코틀린의 방법 : 자바에서 긴 문법을 대폭 낮춤)
    fun findAll() : List<PostReadDto> = postRepository.findAll()
        .map { post ->
            PostReadDto(
                userId = post.user.id,
                nickname = post.user.nickname,
                postId = post.id,
                title = post.title,
                content = post.content
            )
        }

    // 유저 id 기반 조회
    fun findByUserId(userId : Long) : List<PostReadDto> = postRepository.findByUser_Id(userId)
        .map { post ->
            PostReadDto(
                userId = post.user.id,
                nickname = post.user.nickname,
                postId = post.id,
                title = post.title,
                content = post.content
            )
        }


    // 게시글 생성
    fun createPost(dto : PostCreateDto) : Post{
        // 예외의 형태가 다음과 같음
        val getUser : User = userRepository.findById(dto.userId).orElseThrow{
            IllegalArgumentException("유저가 없음")
        }

        val post = Post(
            title = dto.title,
            content = dto.content,
            user = getUser
        )

        return postRepository.save(post)
    }


}