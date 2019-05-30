package com.mlproject.mlproject.classifier.controller

class EvaluateRequest(val sessionId: Long,
                      val evaluationName: String,
                      val classifierName: String,
                      val classAttribute: String)