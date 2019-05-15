package com.mlproject.mlproject.session

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import com.mlproject.mlproject.misc.TrainingAttribute
import weka.core.Instances
import weka.core.TestInstances

class Session(val sessionId : Long) {

    lateinit var trainingInstances : Instances

    lateinit var testInstances: TestInstances

    lateinit var classifierWrapper: AbstractClassifierWrapper

    val trainingAttributes =  mutableListOf<TrainingAttribute>()
}