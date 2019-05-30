package com.mlproject.mlproject.classifier.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@WebMvcTest(ClassifierController::class)
class FetchAllClassifiersTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldFetchAllClassifiers(){
        mockMvc.perform(
                MockMvcRequestBuilders.get("/get-classifiers"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

}