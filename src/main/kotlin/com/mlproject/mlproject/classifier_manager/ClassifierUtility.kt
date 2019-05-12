package com.mlproject.mlproject.classifier_manager

import com.mlproject.mlproject.classifier_manager.classifier.AbstractClassifierWrapper
import com.mlproject.mlproject.classifier_manager.classifier.NaiveBayesWrapper

object ClassifierUtility{

    private val classifierMap = mutableMapOf<ClassifierType, Class<out AbstractClassifierWrapper>>()

    init {
        classifierMap[ClassifierType.NAIVE_BAYES] = NaiveBayesWrapper::class.java
    }

    fun getClassifierWrapper(classifierName : String) : AbstractClassifierWrapper{
        val convertedName = classifierName.replace(' ','_').toUpperCase()
        val enumKey = ClassifierType.valueOf(convertedName)
        return classifierMap[enumKey]!!.newInstance()
    }
}