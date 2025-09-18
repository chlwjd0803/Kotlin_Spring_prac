package com.example.coconut_was.gcs

import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import java.io.IOException

@Configuration
class GcpConfig {

    @Value("\${spring.cloud.gcp.credentials.location}")
    private lateinit var gcpCredentials: Resource

    @Value("\${spring.cloud.gcp.project-id}")
    private lateinit var projectId: String

    @Bean
    @Throws(IOException::class)
    fun storage(): Storage {
        val credentials = GoogleCredentials.fromStream(gcpCredentials.inputStream)
        return StorageOptions.newBuilder()
            .setProjectId(projectId)
            .setCredentials(credentials)
            .build()
            .service
    }
}