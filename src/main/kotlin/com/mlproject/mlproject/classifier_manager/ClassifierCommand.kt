package com.mlproject.mlproject.classifier_manager

import com.mlproject.mlproject.session.SessionManager
import org.springframework.http.HttpStatus


fun setClassifierOnSession(request: SetClassifierRequest): SetClassifierResponse {
    val session = SessionManager.getSession(request.sessionId)
    val classifier = ClassifierUtility.getClassifierWrapper(request.classifierName)
    session.classifierWrapper = classifier
    return SetClassifierResponse(session.sessionId, classifier)
}


fun fetchAllClassifiers() : FetchClassifiersResponse{
    val classifierList = ClassifierType.values().toList().map {
        it -> it.toString().replace('_',' ')}
    return FetchClassifiersResponse(classifierList)
}