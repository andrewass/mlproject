package com.mlproject.mlproject.preprocess.controller

import com.google.gson.Gson
import com.mlproject.mlproject.preprocess.storageservice.FileService
import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest(PreprocessController::class)
class UploadFileTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var fileService: FileService

    @BeforeEach
    fun init() {
        initMocks(this)
    }


    @Disabled
    @Test
    fun test1() {
        mockMvc.perform(MockMvcRequestBuilders.post("upload-file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createRequestBodyJSON()))
                .andExpect(status().isOk)

    }

    private fun createRequestBodyJSON(): String {
        val gson = Gson()
        return gson.toJson("")
    }
}
