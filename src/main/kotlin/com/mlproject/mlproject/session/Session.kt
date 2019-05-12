package com.mlproject.mlproject.session

import com.mlproject.mlproject.classifier_manager.classifier.AbstractClassifierWrapper
import com.mlproject.mlproject.general.TrainingAttribute
import weka.classifiers.AbstractClassifier
import weka.classifiers.Classifier
import weka.core.Instances
import weka.core.TestInstances

class Session(val sessionId : Long) {

    lateinit var trainingInstances : Instances

    lateinit var testInstances: TestInstances

    lateinit var classifierWrapper: AbstractClassifierWrapper

    val trainingAttributes =  mutableListOf<TrainingAttribute>()
}