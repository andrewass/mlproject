package com.mlproject.mlproject.classifier_manager.classifier

import weka.classifiers.AbstractClassifier
import weka.classifiers.bayes.NaiveBayes

class NaiveBayesWrapper : AbstractClassifierWrapper() {
    override val classifier = NaiveBayes()
}