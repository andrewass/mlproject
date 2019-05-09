package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.general.Session
import com.mlproject.mlproject.general.SessionManager
import com.mlproject.mlproject.general.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun executeUploadFile(request: UploadFileRequest): UploadFileResponse {
    val multiplier = 1000000000
    val session: Session?
    val createdInstances = request.dataSource.dataSet

    if (request.sessionId == 0L) {
        val newSessionId = (Math.random() * multiplier).toLong()
        session = Session(newSessionId)
        SessionManager.sessionMap[newSessionId] = session
    } else {
        session = SessionManager.sessionMap[request.sessionId]
    }

    session?.let {
        session.trainingInstances = createdInstances
        val attributeList = createdInstances.enumerateAttributes().toList()
        for (i in 0 until attributeList.size) {
            val trainingAttribute = TrainingAttribute(attributeList.get(i).name(), createdInstances.attributeStats(i))
            session.trainingAttributes.add(trainingAttribute)
        }
    }
    return UploadFileResponse(session!!.trainingAttributes, session.sessionId, ResponseEntity(HttpStatus.OK))
}

