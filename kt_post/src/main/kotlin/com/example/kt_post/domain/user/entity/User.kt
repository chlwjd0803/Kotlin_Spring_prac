package com.example.kt_post.domain.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
open class User(
    var nickname: String,
    var email: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long = 0
}