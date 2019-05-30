package com.mlproject.mlproject.preprocess.controller

import com.google.gson.Gson
import com.mlproject.mlproject.preprocess.storageservice.FileService
import com.mlproject.mlproject.session.Session
import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import weka.core.converters.ConverterUtils
import java.io.InputStream

@WebMvcTest(PreprocessController::class)
class RemoveAttributeTest {

    private val SESSION_ID = 500L

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var fileService: FileService

    lateinit var session: Session

    @BeforeEach
    fun init() {
        SessionManager.sessionMap.clear()
        SessionManager.sessionMap[SESSION_ID] = createSession()
    }

    @Test
    fun shouldReturnOkWhenRemovingAttribute() {
        mockMvc.perform(
                post("/remove-attribute")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(status().isOk)
    }

    private fun createSession(): Session {
        val session = Session(SESSION_ID)
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        session.trainingInstances = ConverterUtils.DataSource(inputStream).dataSet
        return session
    }

    private fun createRequestBody(): String {
        val removeAttributeRequest = RemoveAttributeRestRequest(SESSION_ID, emptyList())
        return Gson().toJson(removeAttributeRequest)
    }
}