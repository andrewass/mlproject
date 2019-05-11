package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.general.Session
import com.mlproject.mlproject.general.SessionManager
import com.mlproject.mlproject.general.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun executeUploadFile(request: UploadFileRequest): UploadFileResponse {
    var session = SessionManager.sessionMap[request.sessionId]
    if (session == null) {
        var newSessionId: Long
        val multiplier = 1000000000L
        do {
            newSessionId = (Math.random() * multiplier).toLong() + 1L
        } while (SessionManager.sessionMap.containsKey(newSessionId))
        session = Session(newSessionId)
        SessionManager.sessionMap[newSessionId] = session
    }

    session.trainingInstances = request.instances
    val attributeList = request.instances.enumerateAttributes().toList()
    for (i in 0 until attributeList.size) {
        val trainingAttribute = TrainingAttribute(attributeList.get(i).name(), request.instances.attributeStats(i))
        session.trainingAttributes.add(trainingAttribute)
    }

    return UploadFileResponse(session.trainingAttributes, session.sessionId, ResponseEntity(HttpStatus.OK))
}

