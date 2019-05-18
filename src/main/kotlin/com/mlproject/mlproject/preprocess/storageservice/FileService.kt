package com.mlproject.mlproject.preprocess.storageservice

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import weka.core.Instances
import weka.core.converters.CSVLoader
import weka.core.converters.ConverterUtils

@Service
class FileService {

    fun retrieveInstances(multipartFile: MultipartFile): Instances {
        val filename = multipartFile.originalFilename!!
        return if (filename.endsWith(".csv")) {
            val csvLoader = CSVLoader()
            csvLoader.setSource(multipartFile.inputStream)
            return csvLoader.dataSet
        } else {
            ConverterUtils.DataSource(multipartFile.inputStream).dataSet
        }
    }
}
