package com.mlproject.mlproject.storageservice

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import weka.core.Instances
import java.io.BufferedReader
import java.io.InputStreamReader

@Service
class FileService() {

    fun createInstances(multipartFile : MultipartFile) : Instances {
        return Instances(BufferedReader(InputStreamReader(multipartFile.inputStream)));
    }
}
