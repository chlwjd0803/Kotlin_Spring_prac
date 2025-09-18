package com.example.coconut_was.domains.project.entity

import jakarta.persistence.*

@Entity
@Table(name = "project_style")
open class ProjectStyle(

    @Column
    var style : String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    var project : Project

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_style_id", nullable = false)
    open var id: Long? = null
}