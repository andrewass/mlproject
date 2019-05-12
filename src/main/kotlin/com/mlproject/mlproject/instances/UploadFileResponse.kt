package com.mlproject.mlproject.instances

import com.mlproject.mlproject.general.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UploadFileResponse(val attributes: List<TrainingAttribute>, val sessionId : Long,
                         val responseCode: ResponseEntity<HttpStatus>)
