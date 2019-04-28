package com.mlproject.mlproject.datasource

import weka.core.converters.ConverterUtils

class TrainingFile(sourcePath : String) {
    val dataSource = ConverterUtils.DataSource(sourcePath)

}