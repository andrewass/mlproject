package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.CustomInstance
import com.mlproject.mlproject.misc.TrainingAttribute
import com.mlproject.mlproject.session.Session
import com.mlproject.mlproject.session.SessionManager
import weka.core.Attribute
import weka.core.Instances
import weka.filters.Filter
import weka.filters.unsupervised.attribute.Remove

/**
 * Populates instances and attributes from an Arff or CSV file
 *
 * @param   request containing the instances from the file
 * @return  response containing a custom representation of the instances and attributes
 */
fun uploadFile(request: UploadFileRequest): UploadFileResponse {
    val session = SessionManager.getSession(request.sessionId)
    session.trainingInstances = request.instances
    updateTrainingAttributesOnSession(session)
    val customInstances = getListOfCustomInstances(session.trainingInstances, session.trainingAttributes)
    return UploadFileResponse(session.trainingAttributes, customInstances, session.sessionId)
}

/**
 * Removing selected attributes from the instances stored on the session
 *
 * @param request containing a list of attributes to be removed
 * @return response containing a custom representation of the instances and attributes
 */
fun removeAttributes(request: RemoveAttributeRequest): RemoveAttributeResponse {
    val session = SessionManager.getSession(request.sessionId)
    val attributeIndicesArrays = getAttributeIndicesArray(
            session.trainingInstances.enumerateAttributes().toList(),
            request.attributeNameList)
    val removeFilter = Remove()
    removeFilter.setAttributeIndicesArray(attributeIndicesArrays)
    removeFilter.invertSelection = false
    removeFilter.setInputFormat(session.trainingInstances)
    session.trainingInstances = Filter.useFilter(session.trainingInstances, removeFilter)
    updateTrainingAttributesOnSession(session)
    val customInstance = getListOfCustomInstances(session.trainingInstances, session.trainingAttributes)
    return RemoveAttributeResponse(session.trainingAttributes, customInstance, session.sessionId)
}

/**
 * @param
 */
private fun updateTrainingAttributesOnSession(session: Session) {
    val attributeList = session.trainingInstances.enumerateAttributes().toList()
    session.trainingAttributes.clear()
    for (i in 0 until attributeList.size) {
        val trainingAttribute = TrainingAttribute(attributeList[i].name(), session.trainingInstances.attributeStats(i))
        session.trainingAttributes.add(trainingAttribute)
    }
}

private fun getListOfCustomInstances(instances: Instances, attributes: List<TrainingAttribute>): List<CustomInstance>{
    val customInstanceList = mutableListOf<CustomInstance>()
    for(instance in instances.enumerateInstances()){
        val attributeValues = instance.toString().split(",")
        val customInstance = CustomInstance()
        for(i in 0 until attributeValues.size){
            customInstance.addAttribute(attributes[i].attributeName, attributeValues[i])
        }
        customInstanceList.add(customInstance)
    }
    return customInstanceList
}

private fun getAttributeIndicesArray(attributeList: List<Attribute>, attributesToRemoveList: List<String>): IntArray {
    val attributeSet = attributesToRemoveList.toSet()
    val indicesList = mutableListOf<Int>()
    for (i in 0 until attributeList.size) {
        if (attributeSet.contains(attributeList[i].name())) {
            indicesList.add(i)
        }
    }
    return indicesList.toIntArray()
}