package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.general.SessionManager
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

        executeUploadFile(firstFileRequest)

        assertEquals(SessionManager.sessionMap.size, 1)
    }

    @Test
    fun shouldFetchPreviouslyCreatedSession() {
        val uploadFileResponse = executeUploadFile(firstFileRequest)
        assertEquals(SessionManager.sessionMap.size, 1)

        val secondRequest = createUploadFileRequest(uploadFileResponse.sessionId)
        executeUploadFile(secondRequest)
        assertEquals(SessionManager.sessionMap.size, 1)
    }

    fun createUploadFileRequest(sessionId : Long) : UploadFileRequest {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        val instances = ConverterUtils.DataSource(inputStream).dataSet
        return UploadFileRequest(instances, sessionId, true)
    }
}