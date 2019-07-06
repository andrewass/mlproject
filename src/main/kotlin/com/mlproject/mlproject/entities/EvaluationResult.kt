package com.mlproject.mlproject.entities

import javax.persistence.*

@Entity(name = "T_EVALUATION_RESULT")
data class EvaluationResult(

    @Id
    @GeneratedValue
    @Column(name = "EVALUATION_RESULT_ID")
    val id : Long,

    @Column(nullable = false)
    val evaluation : String,

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    val user : User

)