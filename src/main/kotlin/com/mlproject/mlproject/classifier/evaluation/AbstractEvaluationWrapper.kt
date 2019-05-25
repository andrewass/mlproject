package com.mlproject.mlproject.classifier.evaluation

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import weka.core.Instances

abstract class AbstractEvaluationWrapper(classifier: AbstractClassifierWrapper, instances: Instances)