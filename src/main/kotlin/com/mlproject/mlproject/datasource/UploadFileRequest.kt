package com.mlproject.mlproject.datasource

import org.springframework.web.multipart.MultipartFile

class UploadFileRequest(val file : MultipartFile, val sessionId : Long) {
}
