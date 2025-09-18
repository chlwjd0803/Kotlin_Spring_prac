package com.example.coconut_was.domains.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
open class User(
    @Column
    var email : String,

    @Column
    var name : String,

    @Column
    var nickname : String,

    @Column
    var password : String,

    @Column
    var role : Role,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    open var id: Long = 0
}