package com.mlproject.mlproject.classifier.controller

import com.mlproject.mlproject.classifier.AbstractClassifierWrapper
import org.springframework.http.HttpStatus

class FetchClassifiersReponse(val classifiers : List<String>, val responseStatus: HttpStatus)