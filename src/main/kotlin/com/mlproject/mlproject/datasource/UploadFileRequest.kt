package com.mlproject.mlproject.datasource

import weka.core.converters.ConverterUtils

class UploadFileRequest(val dataSource: ConverterUtils.DataSource, val sessionId: Long, val isTraining: Boolean)
