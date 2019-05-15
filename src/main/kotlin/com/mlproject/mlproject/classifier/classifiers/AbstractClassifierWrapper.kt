package com.mlproject.mlproject.classifier

import weka.classifiers.AbstractClassifier


abstract class AbstractClassifierWrapper {

     abstract val classifier : AbstractClassifier

}