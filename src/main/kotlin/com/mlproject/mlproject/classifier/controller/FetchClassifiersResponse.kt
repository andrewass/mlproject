package com.mlproject.mlproject.classifier.controller

import org.springframework.http.HttpStatus

class FetchClassifiersResponse(val classifiers : List<ClassifierResponse>, val responseStatus: HttpStatus)