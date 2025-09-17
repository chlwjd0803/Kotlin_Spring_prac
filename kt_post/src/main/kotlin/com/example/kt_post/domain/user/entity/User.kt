package com.example.kt_post.domain.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    var nickname: String,
    var email: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}