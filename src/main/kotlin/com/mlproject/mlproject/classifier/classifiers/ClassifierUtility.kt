package com.mlproject.mlproject.classifier.classifiers

import com.mlproject.mlproject.classifier.classifiers.ClassifierType.*
import com.mlproject.mlproject.classifier.controller.ClassifierResponse
import weka.core.Instances


fun getClassifierWrapper(classifierName: String, instances : Instances): AbstractClassifierWrapper {
    val convertedName = classifierName.replace(' ', '_').toUpperCase()
    return when (valueOf(convertedName)) {
        NAIVE_BAYES -> NaiveBayesWrapper()
        C4_5_DECISION_TREE -> C45Wrapper(instances)
        else -> LogisticRegressionWrapper()
    }
}

fun convertToClassifierResponse(classifierNameList: List<String>): List<ClassifierResponse> {
    return classifierNameList
            .map { it -> ClassifierResponse(it.toLowerCase(), it.toUpperCase()) }
            .toList()
}