package com.mlproject.mlproject.classifier.command

import com.mlproject.mlproject.classifier.classifiers.AbstractClassifierWrapper

class SetClassifierResponse(val sessionId: Long, val classifierWrapper: AbstractClassifierWrapper)