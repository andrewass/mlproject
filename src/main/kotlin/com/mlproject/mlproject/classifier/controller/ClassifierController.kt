package com.mlproject.mlproject.classifier.controller

import com.mlproject.mlproject.classifier.classifiers.convertToClassifierResponse
import com.mlproject.mlproject.classifier.command.fetchAllClassifiers
import com.mlproject.mlproject.classifier.command.runEvaluation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class ClassifierController {

    @GetMapping("/get-classifiers")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getClassifiers(): FetchClassifiersResponse {
        val response = fetchAllClassifiers()
        return FetchClassifiersResponse(response.classifierList, HttpStatus.OK)
    }

    @PostMapping("/evaluate")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun evaluate(@RequestBody request: EvaluateRequest): EvaluateRestResponse {
            val evaluationSummary = runEvaluation(request)
        return EvaluateRestResponse(evaluationSummary, HttpStatus.OK)
    }
}
