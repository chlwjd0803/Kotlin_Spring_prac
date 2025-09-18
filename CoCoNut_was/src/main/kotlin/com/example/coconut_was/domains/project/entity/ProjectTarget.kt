package com.example.coconut_was.domains.project.entity

import jakarta.persistence.*

@Entity
@Table(name = "project_target")
open class ProjectTarget(

    @Column
    var target : String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project : Project

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long = 0
}