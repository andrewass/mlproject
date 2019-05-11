package com.mlproject.mlproject.general

import weka.classifiers.Classifier
import weka.core.Instances
import weka.core.TestInstances

class Session(val sessionId : Long) {

    lateinit var trainingInstances : Instances

    lateinit var testInstances: TestInstances

    lateinit var classifier: Classifier

    val trainingAttributes =  mutableListOf<TrainingAttribute>()
}