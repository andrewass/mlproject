package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import weka.core.converters.ConverterUtils
import java.io.InputStream

@SpringBootTest
class RemoveAttributesTest {

    var sessionId = 0L

    @BeforeEach
    fun initEach(){
        val uploadFileRequest = createUploadFileRequest(0L)
        val response = uploadFile(uploadFileRequest)
        sessionId = response.sessionId
    }

    @Test
    fun shouldRemoveSpecifiedAttributesFromExistingInstances(){
        val session = SessionManager.getSession(sessionId)
        val attributeCountBefore = session.trainingInstances.numAttributes()
        val removeAttributeRequest = RemoveAttributeRequest(sessionId, listOf("sepalwidth","petalwidth" ))
        val response = removeAttributes(removeAttributeRequest)
        assertEquals(session.trainingInstances.numAttributes(), attributeCountBefore-2)
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

    fun createUploadFileRequest(sessionId : Long) : UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}