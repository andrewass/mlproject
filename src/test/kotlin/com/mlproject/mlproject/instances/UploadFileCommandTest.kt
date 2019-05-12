package com.mlproject.mlproject.instances

import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import weka.core.converters.ConverterUtils
import java.io.InputStream

@SpringBootTest
class UploadFileCommandTest {

    lateinit var firstFileRequest: UploadFileRequest

    @BeforeEach
    fun setup() {
        SessionManager.sessionMap.clear()
        firstFileRequest = createUploadFileRequest(0)
    }

    @Test
    fun shouldCreateNewSessionWhenNonePreviouslyExists() {
        assertEquals(SessionManager.sessionMap.size, 0)

        uploadFile(firstFileRequest)

        assertEquals(SessionManager.sessionMap.size, 1)
    }

    @Test
    fun shouldFetchPreviouslyCreatedSession() {
        val uploadFileResponse = uploadFile(firstFileRequest)
        assertEquals(SessionManager.sessionMap.size, 1)

        val secondRequest = createUploadFileRequest(uploadFileResponse.sessionId)
        uploadFile(secondRequest)
        assertEquals(SessionManager.sessionMap.size, 1)
    }

    fun createUploadFileRequest(sessionId : Long) : UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}