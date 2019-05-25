package com.mlproject.mlproject.classifier.classifiers

import com.mlproject.mlproject.classifier.classifiers.ClassifierType.NAIVE_BAYES
import com.mlproject.mlproject.classifier.classifiers.ClassifierType.valueOf
import com.mlproject.mlproject.classifier.controller.ClassifierResponse


fun getClassifierWrapper(classifierName: String): AbstractClassifierWrapper {
    val convertedName = classifierName.replace(' ', '_').toUpperCase()
    return when (valueOf(convertedName)) {
        NAIVE_BAYES -> NaiveBayesWrapper()
        else -> LogisticRegressionWrapper()
    }
}

fun convertToClassifierResponse(classifierNameList: List<String>): List<ClassifierResponse> {
    return classifierNameList
            .map { it -> ClassifierResponse(it.toLowerCase(), it.toUpperCase()) }
            .toList()
}