package com.mlproject.mlproject.instances

import com.mlproject.mlproject.session.SessionManager
import com.mlproject.mlproject.general.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun uploadFile(request: UploadFileRequest): UploadFileResponse {
    val session = SessionManager.getSession(request.sessionId)
    session.trainingInstances = request.instances
    val attributeList = request.instances.enumerateAttributes().toList()
    for (i in 0 until attributeList.size) {
        val trainingAttribute = TrainingAttribute(attributeList.get(i).name(), request.instances.attributeStats(i))
        session.trainingAttributes.add(trainingAttribute)
    }
    return UploadFileResponse(session.trainingAttributes, session.sessionId, ResponseEntity(HttpStatus.OK))
}

