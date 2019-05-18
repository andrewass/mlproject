package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.TrainingAttribute
import com.mlproject.mlproject.session.Session
import com.mlproject.mlproject.session.SessionManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import weka.core.Attribute


fun uploadFile(request: UploadFileRequest): UploadFileResponse {
    val session = SessionManager.getSession(request.sessionId)
    session.trainingInstances = request.instances
    updateTrainingAttributesOnSession(session)
    return UploadFileResponse(session.trainingAttributes, session.sessionId, ResponseEntity(HttpStatus.OK))
}

fun removeAttribute(request: RemoveAttributeRequest): RemoveAttributeResponse {
    val session = SessionManager.getSession(request.sessionId)
    val attributeList = session.trainingInstances.enumerateAttributes().toList()
    request.attributeNameList.forEach {
        val attributeIndex = findIndexOfGivenAttribute(attributeList, it)
        session.trainingInstances.deleteAttributeAt(attributeIndex)
    }
    updateTrainingAttributesOnSession(session)
    return RemoveAttributeResponse(session.trainingAttributes, session.sessionId)
}

private fun updateTrainingAttributesOnSession(session: Session) {
    val attributeList = session.trainingInstances.enumerateAttributes().toList()
    session.trainingAttributes.clear()
    for (i in 0 until attributeList.size) {
        val trainingAttribute = TrainingAttribute(attributeList[i].name(), session.trainingInstances.attributeStats(i))
        session.trainingAttributes.add(trainingAttribute)
    }
}

private fun findIndexOfGivenAttribute(attributeList: List<Attribute>, attributeName: String): Int {
    for(i in 0 until attributeList.size){
        if(attributeName.equals(attributeList[i].name())){
            return i;
        }
    }
    return 0;
}

