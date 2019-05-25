package com.mlproject.mlproject.classifier.controller

import com.mlproject.mlproject.classifier.command.FetchClassifiersResponse
import org.springframework.http.HttpStatus

class FetchClassifiersResponse(val classifiers: List<String>, val responseStatus: HttpStatus)