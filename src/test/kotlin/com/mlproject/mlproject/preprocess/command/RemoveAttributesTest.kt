package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import weka.core.converters.ConverterUtils
import java.io.InputStream

@SpringBootTest
class RemoveAttributesTest {

    var sessionId = 0L
    val reducedAttributeCount = 3
    val instanceCount = 150
    val fileName = "iris.arff"

    @BeforeEach
    fun initEach(){
        val uploadFileRequest = createUploadFileRequest(sessionId)
        val response = uploadFile(uploadFileRequest)
        sessionId = response.sessionId
    }

    @Test
    fun shouldRemoveSpecifiedAttributesFromExistingInstances(){
        val session = SessionManager.getSession(sessionId)
        val removeAttributeRequest = RemoveAttributeRequest(sessionId, listOf("sepalwidth","petalwidth" ))
        val response = removeAttributes(removeAttributeRequest)

        assertEquals(session.trainingInstances.numAttributes(), reducedAttributeCount)
    }

    @Test
    fun shouldNotRemoveAttributesWhenSpecifiedAttributeNameDoesNotExist(){
        val session = SessionManager.getSession(sessionId)
        val attributeCountBefore = session.trainingInstances.numAttributes()
        val removeAttributeRequest = RemoveAttributeRequest(sessionId, listOf("non-existing"))
        val response = removeAttributes(removeAttributeRequest)
        assertEquals(session.trainingInstances.numAttributes(), attributeCountBefore)
    }

    @Test
    fun shouldNotRemoveAttributesWhenNoAttributesAreSpecified(){
        val session = SessionManager.getSession(sessionId)
        val attributeCountBefore = session.trainingInstances.numAttributes()
        val removeAttributeRequest = RemoveAttributeRequest(sessionId, emptyList())
        val response = removeAttributes(removeAttributeRequest)
        assertEquals(session.trainingInstances.numAttributes(), attributeCountBefore)
    }

    @Test
    fun shouldNotRemoveSpecifiedAttributesWhenTheyArePreviouslyRemoved(){
        val session = SessionManager.getSession(sessionId)
        var attributeCountBefore = session.trainingInstances.numAttributes()
        val removeAttributeRequest = RemoveAttributeRequest(sessionId, listOf("sepalwidth","petalwidth" ))
        removeAttributes(removeAttributeRequest)
        val response = removeAttributes(removeAttributeRequest)
        assertEquals(session.trainingInstances.numAttributes(), attributeCountBefore-2)
    }

    private fun assertExpectedResponse(uploadFileResponse: UploadFileResponse){
        assertEquals(uploadFileResponse.attributes.size, reducedAttributeCount)
        assertEquals(uploadFileResponse.instances.size, instanceCount)
        Assertions.assertNotEquals(uploadFileResponse.sessionId, 0L)

        assertEquals(uploadFileResponse.instances[0].attributeNameList.size, reducedAttributeCount)
        assertEquals(uploadFileResponse.instances[0].attributeValueList.size, reducedAttributeCount)
        assertEquals(uploadFileResponse.instances[0].attributeNameList[0], "sepallength")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[1], "petallength")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[2], "class")
    }

    private fun createUploadFileRequest(sessionId : Long) : UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}