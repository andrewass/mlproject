package com.mlproject.mlproject.storageservice

import com.mlproject.mlproject.datasource.UploadFileRequest
import com.mlproject.mlproject.datasource.UploadFileResponse
import com.mlproject.mlproject.datasource.executeUploadFile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class FileController {

    @Autowired
    lateinit var fileservice: FileService

    @PostMapping("/upload-file")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun handleFileUploadV2(@RequestParam("file") file: MultipartFile,
                           @RequestParam("sessionId") sessionId: Long,
                           @RequestParam("isTraining") isTraining: Boolean): UploadFileResponse {
        val dataSource = fileservice.createDataSource(file)
        val uploadFileRequest = UploadFileRequest(dataSource, sessionId, isTraining)
        return executeUploadFile(uploadFileRequest)
    }
}