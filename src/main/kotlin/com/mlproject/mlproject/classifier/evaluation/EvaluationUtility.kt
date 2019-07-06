package com.mlproject.mlproject.classifier.evaluation

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import weka.core.Instances


fun getSelectedEvaluation(
        evaluationOption: String,
        instances: Instances,
        classifierWrapper: AbstractClassifierWrapper): AbstractEvaluationWrapper? {
    val convertedEvaluationName = evaluationOption.replace(' ', '_').toUpperCase()
    return when (EvaluationType.valueOf(convertedEvaluationName)) {
        EvaluationType.CROSS_VALIDATION -> CrossValidation(classifierWrapper, instances)
        else -> null
    }
}


fun setClassIndexOnInstances(attributeName : String, instances: Instances){
    val classAttribute = instances.enumerateAttributes().toList()
            .first { it.name().equals(attributeName)}
    instances.setClassIndex(classAttribute.index())
}