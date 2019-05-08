package com.mlproject.mlproject.datasource

import com.mlproject.mlproject.general.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UploadFileResponse(val attributes: List<TrainingAttribute>, val responseCode: ResponseEntity<HttpStatus>)
