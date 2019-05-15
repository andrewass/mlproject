package com.mlproject.mlproject.classifier.classifiers

import weka.classifiers.bayes.NaiveBayes

class NaiveBayesWrapper : AbstractClassifierWrapper() {
    override val classifier = NaiveBayes()
}