package com.mlproject.mlproject.classifier.controller

import com.mlproject.mlproject.classifier.command.SetClassifierRequest
import com.mlproject.mlproject.classifier.command.fetchAllClassifiers
import com.mlproject.mlproject.classifier.command.setClassifierOnSession
import com.mlproject.mlproject.misc.RestResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ClassifierController {

    @PostMapping("/set-classifier")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun setClassifier(@RequestParam("sessionId") sessionId: Long,
                      @RequestParam("classifier") classifier: String): RestResponse {
        val request = SetClassifierRequest(sessionId, classifier)
        val response = setClassifierOnSession(request)
        return RestResponse(HttpStatus.OK)
    }

    @GetMapping("/get-classifiers")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getClassifiers() : FetchClassifiersReponse{
        val response = fetchAllClassifiers()
        return FetchClassifiersReponse(response.classifierList, HttpStatus.OK)
    }
}

