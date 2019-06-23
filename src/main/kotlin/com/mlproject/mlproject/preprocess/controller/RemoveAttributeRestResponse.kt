package com.mlproject.mlproject.preprocess.controller

import com.mlproject.mlproject.misc.CustomInstance
import com.mlproject.mlproject.misc.TrainingAttribute
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class RemoveAttributeRestResponse(val attributes: List<TrainingAttribute>,
                                  val sessionId : Long,
                                  val responseCode: HttpStatus,
                                  val instances : List<CustomInstance>)