package com.mlproject.mlproject.classifier

import com.mlproject.mlproject.classifier.classifiers.ClassifierType
import com.mlproject.mlproject.classifier.command.SetClassifierRequest
import com.mlproject.mlproject.classifier.command.fetchAllClassifiers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassifierCommandTest {

    lateinit var setClassifierRequest: SetClassifierRequest

    @BeforeEach
    fun setup() {
        setClassifierRequest = SetClassifierRequest(0L, "Naive Bayes")
    }

    @Test
    fun shouldFetchAllClassifiers() {
        val response = fetchAllClassifiers()
        assertEquals(response.classifierList.size, ClassifierType.values().size)
    }
}