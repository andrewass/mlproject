package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.TrainingAttribute
import com.mlproject.mlproject.session.Session
import com.mlproject.mlproject.session.SessionManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import weka.core.Attribute
import weka.filters.Filter
import weka.filters.unsupervised.attribute.Remove


fun uploadFile(request: UploadFileRequest): UploadFileResponse {
    val session = SessionManager.getSession(request.sessionId)
    session.trainingInstances = request.instances
    updateTrainingAttributesOnSession(session)
    return UploadFileResponse(session.trainingAttributes, session.sessionId, ResponseEntity(HttpStatus.OK))
}

fun removeAttributes(request: RemoveAttributeRequest): RemoveAttributeResponse {
    val session = SessionManager.getSession(request.sessionId)
    val attributeIndicesArrays = getAttributeIndicesArray(
            session.trainingInstances.enumerateAttributes().toList(),
            request.attributeNameList)
    val removeFilter = Remove()
    removeFilter.setAttributeIndicesArray(attributeIndicesArrays)
    removeFilter.invertSelection= false
    removeFilter.setInputFormat(session.trainingInstances)
    session.trainingInstances = Filter.useFilter(session.trainingInstances, removeFilter)
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

private fun getAttributeIndicesArray(attributeList: List<Attribute>, attributesToRemoveList: List<String>): IntArray {
    val attributeSet = attributesToRemoveList.toSet();
    val indicesList = mutableListOf<Int>()
    for (i in 0 until attributeList.size) {
        if (attributeSet.contains(attributeList[i].name())) {
            indicesList.add(i);
        }
    }
    return indicesList.toIntArray();
}

