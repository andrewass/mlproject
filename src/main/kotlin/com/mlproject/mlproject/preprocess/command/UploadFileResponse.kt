package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class UploadFileResponse(val attributes: List<TrainingAttribute>, val sessionId : Long)
