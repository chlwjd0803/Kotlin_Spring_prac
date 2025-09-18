package com.example.coconut_was.domains.project.entity

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
    open var id: Long? = null
}