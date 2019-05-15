package com.mlproject.mlproject.classifier.command

import com.mlproject.mlproject.classifier.ClassifierType
import com.mlproject.mlproject.classifier.ClassifierUtility
import com.mlproject.mlproject.classifier.FetchClassifiersResponse
import com.mlproject.mlproject.session.SessionManager


fun setClassifierOnSession(request: SetClassifierRequest): SetClassifierResponse {
    val session = SessionManager.getSession(request.sessionId)
    val classifierWrapper = ClassifierUtility.getClassifierWrapper(request.classifierName)
    session.classifierWrapper = classifierWrapper
    return SetClassifierResponse(session.sessionId, classifierWrapper)
}

fun fetchAllClassifiers(): FetchClassifiersResponse {
    val classifierList = ClassifierType.values().toList().map { it -> it.toString().replace('_', ' ') }
    return FetchClassifiersResponse(classifierList)
}