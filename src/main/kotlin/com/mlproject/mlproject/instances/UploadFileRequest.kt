package com.mlproject.mlproject.instances

import weka.core.Instances

class UploadFileRequest(val instances: Instances, val sessionId: Long, val isTraining: Boolean)
