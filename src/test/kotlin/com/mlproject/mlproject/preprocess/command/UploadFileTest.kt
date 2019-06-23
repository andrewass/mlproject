package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import weka.core.converters.ConverterUtils
import java.io.InputStream

@SpringBootTest
class UploadFileTest {

    val attributeCount = 5
    val instanceCount = 150;
    val fileName = "iris.arff"

    lateinit var firstFileRequest: UploadFileRequest

    @BeforeEach
    fun setup() {
        SessionManager.sessionMap.clear()
        firstFileRequest = createUploadFileRequest(0)
    }

    @Test
    fun shouldCreateNewSessionWhenNonePreviouslyExists() {
        assertEquals(SessionManager.sessionMap.size, 0)
        val uploadFileResponse = uploadFile(firstFileRequest)
        assertEquals(SessionManager.sessionMap.size, 1)
        assertExpectedResponse(uploadFileResponse)
    }

    @Test
    fun shouldFetchPreviouslyCreatedSession() {
        var uploadFileResponse = uploadFile(firstFileRequest)
        assertEquals(SessionManager.sessionMap.size, 1)
        assertExpectedResponse(uploadFileResponse)

        val secondRequest = createUploadFileRequest(uploadFileResponse.sessionId)
        uploadFileResponse = uploadFile(secondRequest)
        assertEquals(SessionManager.sessionMap.size, 1)
        assertExpectedResponse(uploadFileResponse)
    }

    private fun assertExpectedResponse(uploadFileResponse: UploadFileResponse){
        assertEquals(uploadFileResponse.attributes.size, attributeCount)
        assertEquals(uploadFileResponse.instances.size, instanceCount)
        assertNotEquals(uploadFileResponse.sessionId, 0L)

        assertEquals(uploadFileResponse.instances[0].attributeNameList.size, attributeCount)
        assertEquals(uploadFileResponse.instances[0].attributeValueList.size, attributeCount)
        assertEquals(uploadFileResponse.instances[0].attributeNameList[0], "sepallength")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[1], "sepalwidth")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[2], "petallength")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[3], "petalwidth")
        assertEquals(uploadFileResponse.instances[0].attributeNameList[4], "class")
    }

    private fun createUploadFileRequest(sessionId: Long): UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}