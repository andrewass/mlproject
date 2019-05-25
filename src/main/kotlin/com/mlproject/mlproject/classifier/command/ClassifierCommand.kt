package com.mlproject.mlproject.classifier.command

import com.mlproject.mlproject.classifier.classifiers.ClassifierType
import com.mlproject.mlproject.classifier.classifiers.getClassifierWrapper
import com.mlproject.mlproject.classifier.controller.EvaluateRequest
import com.mlproject.mlproject.classifier.evaluation.CrossValidation
import com.mlproject.mlproject.classifier.evaluation.getSelectedEvaluation
import com.mlproject.mlproject.session.SessionManager


fun fetchAllClassifiers(): FetchClassifiersResponse {
    val classifierList = ClassifierType.values().toList()
            .map { it -> it.toString().replace('_', ' ') }
            .map { it -> it.toLowerCase().capitalizeEachWord() }
    return FetchClassifiersResponse(classifierList)
}

fun runEvaluation(request: EvaluateRequest): String {
    val session = SessionManager.getSession(request.sessionId)
    session.trainingInstances.setClassIndex(session.trainingInstances.numAttributes() - 1)
    val classifierWrapper = getClassifierWrapper(request.classifierName)
    val evaluationWrapper = getSelectedEvaluation(request.evaluationName, session.trainingInstances, classifierWrapper)
    if (evaluationWrapper is CrossValidation) {
        return evaluationWrapper.runCrossValidation()
    }
    return ""
}

private fun String.capitalizeEachWord() = this.split(' ')
        .map { it -> it.capitalize() }
        .joinToString(separator = " ")

