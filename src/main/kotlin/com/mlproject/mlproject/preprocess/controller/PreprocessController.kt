package com.mlproject.mlproject.preprocess.controller

import com.mlproject.mlproject.preprocess.command.*
import com.mlproject.mlproject.preprocess.storageservice.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController
class PreprocessController {

    @Autowired
    lateinit var fileService: FileService

    @PostMapping("/upload-file")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun handleFileUpload(@RequestParam("file") file: MultipartFile,
                         @RequestParam("sessionId") sessionId: Long,
                         @RequestParam("isTraining") isTraining: Boolean): UploadFileResponse {
        val instances = fileService.retrieveInstances(file)
        val uploadFileRequest = UploadFileRequest(instances, sessionId, isTraining)
        return uploadFile(uploadFileRequest)
    }

    @PostMapping("/remove-attribute")
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun handleAttributeRemoval(@RequestBody request : RemoveAttributeRestRequest): RemoveAttributeRestResponse {
        val removeAttributeRequest = RemoveAttributeRequest(request.sessionId, request.attributeNameList)
        val response = removeAttributes(removeAttributeRequest)
        return RemoveAttributeRestResponse(response.attributes, response.sessionId, HttpStatus.OK, response.instances)
    }
}