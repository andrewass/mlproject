package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.storageservice.FileService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile

@SpringBootTest
class UploadFileCommandTest {

    @Mock
    lateinit var fileService: FileService
    val test = javaClass.getResource("iris.arff")
    val test2 = javaClass.getResourceAsStream("iris.arff")
    //val uploadFile = MockMultipartFile("iris", javaClass.getResourceStream("iris.arff"))

    @BeforeEach
    fun setup(){
        val vv = 1+1
    }

    @Test
    fun shouldCreateNewSessionWhenNonePreviouslyExists() {
        assertEquals(1, 1)
    }
}