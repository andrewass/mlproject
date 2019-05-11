package com.mlproject.mlproject.datasource

import weka.core.Instances

class UploadFileRequest(val instances: Instances, val sessionId: Long, val isTraining: Boolean)
