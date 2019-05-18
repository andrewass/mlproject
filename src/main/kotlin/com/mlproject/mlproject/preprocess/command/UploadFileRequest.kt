package com.mlproject.mlproject.preprocess.command

import weka.core.Instances

class UploadFileRequest(val instances: Instances, val sessionId: Long, val isTraining: Boolean)
