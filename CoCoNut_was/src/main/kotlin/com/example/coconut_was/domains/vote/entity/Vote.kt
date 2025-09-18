package com.example.coconut_was.domains.vote.entity

import com.example.coconut_was.domains.user.entity.User
import com.example.coconut_was.domains.submission.entity.Submission
import jakarta.persistence.*

@Entity
@Table(name = "vote")
open class Vote(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    var submission : Submission

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0
}