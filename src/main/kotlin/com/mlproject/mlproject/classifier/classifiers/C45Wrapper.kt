package com.mlproject.mlproject.classifier.classifiers

import weka.classifiers.trees.J48
import weka.core.Instances

class C45Wrapper(instances: Instances) : AbstractClassifierWrapper() {
    override val classifier = J48()

    init {
        classifier.buildClassifier(instances)
    }
}