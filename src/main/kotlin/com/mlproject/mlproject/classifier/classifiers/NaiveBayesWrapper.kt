package com.mlproject.mlproject.classifier

import weka.classifiers.bayes.NaiveBayes

class NaiveBayesWrapper : AbstractClassifierWrapper() {
    override val classifier = NaiveBayes()
}