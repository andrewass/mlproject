package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.CustomInstance
import com.mlproject.mlproject.misc.TrainingAttribute

class RemoveAttributeResponse(val attributes: List<TrainingAttribute>,
                              val instances: List<CustomInstance>,
                              val sessionId: Long)