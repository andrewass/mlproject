package com.mlproject.mlproject.preprocess.controller

import com.google.gson.Gson
import com.mlproject.mlproject.preprocess.command.UploadFileRequest
import com.mlproject.mlproject.preprocess.storageservice.FileService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import weka.core.Instances
import weka.core.converters.ConverterUtils
import java.io.InputStream


@WebMvcTest(PreprocessController::class)
class UploadFileTest {

    @MockBean
    lateinit var fileService: FileService

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun init() {
        initMocks(this)
    }

    @Disabled
    @Test
    fun test1() {
        val mockMultipartFile = MockMultipartFile("data", "filename.txt", "text/plain",
                "random content".toByteArray())
        val instances = createInstances()
        `when`(fileService.retrieveInstances(mockMultipartFile)).thenReturn(instances)

        mockMvc.perform(
                MockMvcRequestBuilders.multipart("upload-file")
                        .file(mockMultipartFile)
                        .param("isTraining", "true")
                        .param("sessionId", "0"))
                .andExpect(status().isOk)


    }

     private fun createInstances(): Instances {
        val inputStream: InputStream = javaClass.classLoader.getResourceAsStream("iris.arff")
        return ConverterUtils.DataSource(inputStream).dataSet
    }

    private fun createRequestBodyJSON(): String {
        val gson = Gson()
        return gson.toJson("")
    }
}
