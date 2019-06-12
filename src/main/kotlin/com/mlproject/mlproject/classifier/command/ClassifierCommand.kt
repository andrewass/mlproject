package com.mlproject.mlproject.classifier.command

import com.mlproject.mlproject.classifier.classifiers.ClassifierType
import com.mlproject.mlproject.classifier.classifiers.getClassifierWrapper
import com.mlproject.mlproject.classifier.controller.EvaluateRequest
import com.mlproject.mlproject.classifier.evaluation.CrossValidation
import com.mlproject.mlproject.classifier.evaluation.getSelectedEvaluation
import com.mlproject.mlproject.classifier.evaluation.setClassIndexOnInstances
import com.mlproject.mlproject.session.SessionManager


fun fetchAllClassifiers(): FetchClassifiersResponse {
    val classifierList = ClassifierType.values().toList()
            .map { it.toString().replace('_', ' ') }
            .map { it.toLowerCase().capitalizeEachWord() }
    return FetchClassifiersResponse(classifierList)
}

fun runEvaluation(request: EvaluateRequest): String {
    val session = SessionManager.getSession(request.sessionId)
    setClassIndexOnInstances(request.classAttribute, session.trainingInstances)
    val classifierWrapper = getClassifierWrapper(request.classifierName, session.trainingInstances)
    val evaluationWrapper = getSelectedEvaluation(request.evaluationName, session.trainingInstances, classifierWrapper)
    if (evaluationWrapper is CrossValidation) {
        return evaluationWrapper.runCrossValidation()
    }
    return ""
}

private fun String.capitalizeEachWord() = this.split(' ')
        .joinToString(separator = " ") { it.capitalize() }

