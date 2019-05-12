package com.mlproject.mlproject.classifier_manager

import com.mlproject.mlproject.classifier_manager.classifier.AbstractClassifierWrapper

class SetClassifierResponse(val sessionId: Long, val classifierWrapper: AbstractClassifierWrapper)