package com.mlproject.mlproject.classifier

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import com.mlproject.mlproject.classifier.classifiers.LogisticRegressionWrapper
import com.mlproject.mlproject.classifier.classifiers.NaiveBayesWrapper
import com.mlproject.mlproject.classifier.controller.ClassifierResponse

object ClassifierUtility {

    private val classifierMap = mutableMapOf<ClassifierType, Class<out AbstractClassifierWrapper>>()

    init {
        classifierMap[ClassifierType.NAIVE_BAYES] = NaiveBayesWrapper::class.java
        classifierMap[ClassifierType.LOGISTIC_REGRESSION] = LogisticRegressionWrapper::class.java
    }

    fun getClassifierWrapper(classifierName: String): AbstractClassifierWrapper {
        val convertedName = classifierName.replace(' ', '_').toUpperCase()
        val enumKey = ClassifierType.valueOf(convertedName)
        return classifierMap[enumKey]!!.newInstance()
    }

    fun convertToClassifierResponse(classifierNameList: List<String>): List<ClassifierResponse> {
        return classifierNameList
                .map { it -> ClassifierResponse(it.toLowerCase(), it.toUpperCase()) }
                .toList()
    }
}