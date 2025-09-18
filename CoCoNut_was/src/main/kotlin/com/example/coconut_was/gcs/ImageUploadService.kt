package com.example.coconut_was.gcs

import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*

@Service
class ImageUploadService(private val storage: Storage) { // 생성자에서 직접 의존성 주입

    @Value("\${spring.cloud.gcp.storage.bucket-name}")
    private lateinit var bucketName: String

    @Throws(IOException::class)
    fun uploadImage(file: MultipartFile): String {
        // 1. 파일 이름이 중복되지 않도록 UUID로 새로운 파일명을 생성
        val ext = file.originalFilename?.substringAfterLast(".", "")
        val fileName = "${UUID.randomUUID()}.$ext"

        // 2. GCS에 파일을 업로드
        val blobInfo = BlobInfo.newBuilder(bucketName, fileName)
            .setContentType(file.contentType)
            .build()

        storage.createFrom(blobInfo, file.inputStream)

        // 3. 업로드된 이미지의 공개 URL을 만들어 반환
        return "https://storage.googleapis.com/$bucketName/$fileName"
    }
}