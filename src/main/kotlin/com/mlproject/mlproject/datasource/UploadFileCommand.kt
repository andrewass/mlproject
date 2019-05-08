package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.general.Session
import com.mlproject.mlproject.general.SessionManager
import com.mlproject.mlproject.general.TrainingAttribute
import com.mlproject.mlproject.storageservice.FileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity


fun executeUploadFile(request: UploadFileRequest, fileService: FileService): UploadFileResponse {
    val MULTIPLIER = 1000000000
    val createdInstances = fileService.createInstances(request.file)
    val session: Session?

    if (request.sessionId == 0L) {
        val newSessionId = (Math.random() * MULTIPLIER).toLong()
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
    return UploadFileResponse(session!!.trainingAttributes, ResponseEntity(HttpStatus.OK))
}

