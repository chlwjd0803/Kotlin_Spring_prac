package com.example.coconut_was.domains.project.entity

import com.example.coconut_was.domains.submission.entity.Submission
import com.example.coconut_was.domains.user.entity.User
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "project")
open class Project(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user : User,

    @Column
    var title : String,

    @Column
    var merchantName : String,

    @Enumerated(EnumType.STRING)
    @Column
    var category : Category,

    @Enumerated(EnumType.STRING)
    @Column
    var businessType : BusinessType,

    @Column(columnDefinition = "TEXT")
    var description : String,

    @Column
    var rewardAmount : Int,

    @Column
    var summary : String?,

    @Column
    var createdAt : LocalDate,

    @Column
    var deadline : LocalDate,

    @Column
    var votingStartDate : LocalDate?,

    @Enumerated(EnumType.STRING)
    @Column
    var status : Status,

    @Column
    var imageUrls : String?

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    open var id: Long = 0

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    var projectColors : MutableList<ProjectColor> = mutableListOf()

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    var projectStyles : MutableList<ProjectStyle> = mutableListOf()

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    var projectTargets : MutableList<ProjectTarget> = mutableListOf()

    @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
    var submissions : MutableList<Submission> = mutableListOf()

    @PrePersist
    fun onCreate(){
        this.createdAt = LocalDate.now()
        this.status = Status.IN_PROGRESS
    }

    fun startVoting(){
        this.status = Status.VOTING
        this.votingStartDate = LocalDate.now()
    }

    fun close(){
        this.status = Status.CLOSED
    }
}