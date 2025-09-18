package com.example.coconut_was.domains.project.entity

import jakarta.persistence.*

@Entity
@Table(name = "project_color")
open class ProjectColor(

    @Column
    var color : String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project : Project
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_color_id", nullable = false)
    open var id: Long = 0
}