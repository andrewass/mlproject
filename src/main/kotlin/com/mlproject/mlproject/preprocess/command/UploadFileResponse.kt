package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.TrainingAttribute
import weka.core.AttributeStats

class UploadFileResponse(val attributes: List<TrainingAttribute>, val sessionId: Long)
