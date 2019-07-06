package com.mlproject.mlproject.entities

import javax.persistence.*

@Entity(name = "T_USER")
data class User(

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    val id : Int,

    @Column(nullable = false)
    val name : String,

    @OneToMany(mappedBy = "user")
    val evaluationResults : List<EvaluationResult> = emptyList()

)