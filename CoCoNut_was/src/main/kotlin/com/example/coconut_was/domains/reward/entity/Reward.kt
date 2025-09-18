package com.example.coconut_was.domains.reward.entity

import com.example.coconut_was.domains.project.entity.Project
import com.example.coconut_was.domains.submission.entity.Submission
import com.example.coconut_was.domains.user.entity.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "reward")
open class Reward(

    @Column
    var rewardAmount : Int,

    @Column
    var rewardedAt : LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project : Project,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id")
    var submission : Submission,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0
}