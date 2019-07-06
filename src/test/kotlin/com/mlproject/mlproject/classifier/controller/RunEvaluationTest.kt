package com.mlproject.mlproject.classifier.controller

import com.google.gson.Gson
import com.mlproject.mlproject.preprocess.controller.RemoveAttributeRestRequest
import com.mlproject.mlproject.session.Session
import com.mlproject.mlproject.session.SessionManager
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import weka.core.converters.ConverterUtils
import java.io.InputStream

@WebMvcTest(ClassifierController::class)
class RunEvaluationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    val SESSION_ID = 500L

    @Test
    fun shouldRunEvaluation(){
        mockMvc.perform(
                MockMvcRequestBuilders.post("/evaluate")
                        .contentType(MediaType.APPLICATION_JSON).content(createRequestBody()))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    private fun createRequestBody(): String {
        val session = createSession()
        val evaluateRequest = EvaluateRequest(SESSION_ID, "Cross Validation",
                "Naive Bayes", "class")
        return Gson().toJson(evaluateRequest)
    }

    private fun createSession(): Session {
        SessionManager.sessionMap.clear();
        val session = Session(SESSION_ID)
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        session.trainingInstances = ConverterUtils.DataSource(inputStream).dataSet
        SessionManager.sessionMap[SESSION_ID] = session
        return session
    }
}