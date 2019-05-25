package com.mlproject.mlproject.classifier.classifiers

import weka.classifiers.AbstractClassifier
import weka.core.Instances


abstract class AbstractClassifierWrapper() {
     abstract val classifier : AbstractClassifier
}