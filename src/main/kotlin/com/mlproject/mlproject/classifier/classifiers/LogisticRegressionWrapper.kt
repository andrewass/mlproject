package com.mlproject.mlproject.classifier.classifiers

import weka.classifiers.AbstractClassifier
import weka.classifiers.functions.SimpleLogistic

class LogisticRegressionWrapper : AbstractClassifierWrapper(){

    override val classifier = SimpleLogistic()
}