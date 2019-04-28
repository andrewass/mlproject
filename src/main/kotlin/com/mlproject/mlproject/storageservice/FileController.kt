package com.mlproject.mlproject.storageservice

import com.mlproject.mlproject.datasource.UploadFileReponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import weka.core.Instances


@RestController
class FileController {

    @Autowired
    lateinit var fileservice : FileService

    @PostMapping("/upload-file")
    fun handleFileUpload(@RequestParam("file") file: MultipartFile): ResponseEntity<Instances> {
        val createdInstances = fileservice.createInstances(file);
        return ResponseEntity(createdInstances, HttpStatus.OK);
    }

    @PostMapping("/upload-filev2")
    fun handleFileUploadV2(@RequestParam("file") file: MultipartFile): UploadFileReponse{
        val createdInstances = fileservice.createInstances(file);
        return UploadFileReponse(ResponseEntity(createdInstances, HttpStatus.OK))
    }
}