package com.example.kt_post.domain.post.entity

import com.example.kt_post.domain.post.dto.PostUpdateDto
import com.example.kt_post.domain.user.entity.User
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "post")
class Post(
    // null 허용이 없기에 초기화 해줘야하는 차이가 존재
    // 클래스 인자가 그냥 생성자 자체
    var title: String,
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0 // 기본값이 존재해야함

    fun update(dto : PostUpdateDto){
        // Elvis 연산자. dto가 null이면 업데이트 안하고 값이 있으면 반영, 코틀린에서 추가됨
        title = dto.title ?: title
        content = dto.content ?: content
    }
}
