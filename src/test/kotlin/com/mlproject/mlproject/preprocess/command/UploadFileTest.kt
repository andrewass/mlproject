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
        assertEquals(uploadFileResponse.instances[0].attributeNameList.size, attributeCount)
        assertEquals(uploadFileResponse.instances[0].attributeValueList.size, attributeCount)
        assertNotEquals(uploadFileResponse.sessionId, 0L)
    }

    private fun createUploadFileRequest(sessionId: Long): UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}