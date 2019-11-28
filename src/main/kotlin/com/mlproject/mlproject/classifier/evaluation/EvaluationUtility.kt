package com.mlproject.mlproject.classifier.evaluation

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper
import weka.core.Instances


fun getSelectedEvaluation(
        evaluationOption: String,
        instances: Instances,
        classifierWrapper: AbstractClassifierWrapper): AbstractEvaluationWrapper? {
    val convertedEvaluationName = evaluationOption.replace(' ', '_').toUpperCase()
    val enumKey = EvaluationType.valueOf(convertedEvaluationName)
    when (enumKey) {
        EvaluationType.CROSS_VALIDATION -> return CrossValidation(classifierWrapper, instances)
    }
    return null
}


fun setClassIndexOnInstances(attributeName : String, instances: Instances){
    val classAttribute = instances.enumerateAttributes().toList()
            .first { it -> it.name().equals(attributeName)}
    instances.setClassIndex(classAttribute.index())
}