package com.example.coconut_was.domains.submission.entity

import com.example.coconut_was.domains.project.entity.Project
import com.example.coconut_was.domains.reward.entity.Reward
import com.example.coconut_was.domains.user.entity.User
import com.example.coconut_was.domains.vote.entity.Vote
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "submission")
open class Submission(

    @Column
    var title : String?,

    @Column(columnDefinition = "TEXT")
    var description : String,

    @Column
    var relatedUrl : String?,

    @Column
    var imageUrl : String?,

    @Column
    var submittedAt : LocalDate,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project : Project,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0

    @OneToMany(mappedBy = "submission", cascade = [CascadeType.ALL], orphanRemoval = true)
    var votes : MutableList<Vote> = mutableListOf()

    @OneToOne(mappedBy = "submission", cascade = [CascadeType.ALL], orphanRemoval = true)
    var reward : Reward? = null
}