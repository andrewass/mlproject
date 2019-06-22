package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.CustomInstance
import com.mlproject.mlproject.misc.TrainingAttribute
import weka.core.AttributeStats
import weka.core.Instance
import weka.core.Instances

data class UploadFileResponse(val attributes: List<TrainingAttribute>,
                              val instances : List<CustomInstance>,
                              val sessionId: Long)
