package com.mlproject.mlproject.general

import weka.core.AttributeStats
import weka.core.Instances

class Session(val sessionId : Long) {

    lateinit var trainingInstances : Instances

    val trainingAttributes =  mutableListOf<TrainingAttribute>()
}