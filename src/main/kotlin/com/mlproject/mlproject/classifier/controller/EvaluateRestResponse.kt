package com.mlproject.mlproject.classifier.controller

import org.springframework.http.HttpStatus

class EvaluateRestResponse(val evaluationSummary: String, val httpStatus: HttpStatus)
