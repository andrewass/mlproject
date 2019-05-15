package com.mlproject.mlproject.classifier

import com.mlproject.mlproject.classifier.classifiers.NaiveBayesWrapper
import com.mlproject.mlproject.classifier.command.SetClassifierRequest
import com.mlproject.mlproject.classifier.command.fetchAllClassifiers
import com.mlproject.mlproject.classifier.command.setClassifierOnSession
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import weka.classifiers.bayes.NaiveBayes

@SpringBootTest
class ClassifierCommandTest{

    lateinit var setClassifierRequest: SetClassifierRequest

    @BeforeEach
    fun setup(){
        setClassifierRequest = SetClassifierRequest(0L, "Naive Bayes")
    }

    @Test
    fun shouldSetClassifierForNaiveBayes(){
        val response = setClassifierOnSession(setClassifierRequest)
        assertEquals(response.classifierWrapper is NaiveBayesWrapper, true)
        assertEquals(response.classifierWrapper.classifier is NaiveBayes , true)
        assertNotEquals(response.sessionId, 0L)
    }

    @Test
    fun shouldFetchAllClassifiers(){
        val response = fetchAllClassifiers()
        assertEquals(response.classifierList.size, ClassifierType.values().size)
    }
}