package com.a_cobra.aws_learning.services;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
  @Autowired
  S3Client s3Client;

  private final String bucketName = "file-uploads";

  public ResponseEntity<String> uploadFile(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    try {
      Path tempFile = Files.createTempFile("temp", fileName);
      Files.write(tempFile, file.getBytes());
      s3Client.putObject(
          PutObjectRequest.builder()
              .bucket(bucketName)
              .key(fileName)
              .build(),
          tempFile);
      return ResponseEntity.ok("File uploaded successfully: " + fileName);
    } catch (Exception e) {
      // Was not able to upload the file
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
          .body("Error uploading the file:\n" + e.getMessage());
    }
  }

  public ResponseEntity<byte[]> findFile(String fileName) {
    GetObjectRequest getObjectRequest = GetObjectRequest.builder()
        .bucket(bucketName)
        .key(fileName)
        .build();

    try (ResponseInputStream<?> inputStream = s3Client.getObject(getObjectRequest)) {
      return ResponseEntity.ok(inputStream.readAllBytes()); // or process as needed
    } catch (Exception e) {
      // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      return ResponseEntity.notFound().build();
    }
  }
}
