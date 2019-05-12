package com.mlproject.mlproject.classifier_manager.classifier

import weka.classifiers.AbstractClassifier


abstract class AbstractClassifierWrapper {

     abstract val classifier : AbstractClassifier

}