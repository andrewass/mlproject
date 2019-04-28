package com.mlproject.mlproject.datasource

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import weka.core.Instances

class UploadFileReponse(val responseEntity: ResponseEntity<Instances>)
