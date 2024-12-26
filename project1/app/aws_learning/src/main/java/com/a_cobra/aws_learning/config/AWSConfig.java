package com.a_cobra.aws_learning.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

@Configuration
public class AWSConfig {
  @Value("${aws.s3.endpoint}")
  private String s3Endpoint;

  @Value("${aws.credentials.id}")
  private String awsId;

  @Value("${aws.credentials.secret}")
  private String awsSecret;

  @Bean
  public S3Client s3Client() {
    return S3Client.builder()
        .endpointOverride(URI.create(
            s3Endpoint))
        .region(Region.SA_EAST_1)
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(
                awsId,
                awsSecret)))
        .build();
  }
}
