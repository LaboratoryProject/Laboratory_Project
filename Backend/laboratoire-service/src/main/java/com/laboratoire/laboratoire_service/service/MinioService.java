package com.laboratoire.laboratoire_service.service;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class MinioService {

    private final MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketName;


    @Value("${minio.endpoint}")
    private String minioEndpoint;

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * Upload a file to MinIO storage
     *
     * @param file MultipartFile to be uploaded
     * @param originalFileName Original filename of the uploaded file
     * @return String filename stored in MinIO
     * @throws IOException if upload fails
     */
    public String uploadFile(MultipartFile file, String originalFileName) throws IOException {
        try {
            // Ensure bucket exists
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            // Generate unique filename
            String uniqueFileName = generateUniqueFileName(originalFileName);

            // Upload file to MinIO
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(uniqueFileName)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build()
            );

            return uniqueFileName;
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IOException("Failed to upload file to MinIO", e);
        }
    }

    /**
     * Delete a file from MinIO storage
     *
     * @param fileName Name of the file to delete
     * @throws IOException if deletion fails
     */
    public void deleteFile(String fileName) throws IOException {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build()
            );
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new IOException("Failed to delete file from MinIO", e);
        }
    }

    /**
     * Generate a unique filename to prevent conflicts
     *
     * @param originalFileName Original filename
     * @return Unique filename with UUID prefix
     */
    private String generateUniqueFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String fileExtension = getFileExtension(originalFileName);
        return uuid + "." + fileExtension;
    }

    /**
     * Extract file extension from filename
     *
     * @param fileName Original filename
     * @return File extension
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * Get the URL of a stored file
     *
     * @param fileName Name of the stored file
     * @return Full URL to access the file
     */

    public String getFileUrl(String fileName) {
        return String.format("%s/%s/%s",
                minioEndpoint,
                bucketName,
                fileName
        );
    }
}