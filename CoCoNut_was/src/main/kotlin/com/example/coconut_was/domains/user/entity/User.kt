package com.example.coconut_was.domains.user.entity

import com.example.coconut_was.domains.project.entity.Project
import com.example.coconut_was.domains.submission.entity.Submission
import com.example.coconut_was.domains.vote.entity.Vote
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

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var projects : MutableList<Project> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var submissions : MutableList<Submission> = mutableListOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var votes : MutableList<Vote> = mutableListOf()

}