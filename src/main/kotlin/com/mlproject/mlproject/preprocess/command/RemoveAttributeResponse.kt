package com.mlproject.mlproject.preprocess.command

import com.mlproject.mlproject.misc.TrainingAttribute

class RemoveAttributeResponse(val trainingAttributes: List<TrainingAttribute>, val sessionId: Long)