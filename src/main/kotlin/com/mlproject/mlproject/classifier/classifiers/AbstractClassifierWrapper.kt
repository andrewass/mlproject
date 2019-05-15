package com.mlproject.mlproject.classifier.classifiers

import weka.classifiers.AbstractClassifier


abstract class AbstractClassifierWrapper {

     abstract val classifier : AbstractClassifier

}