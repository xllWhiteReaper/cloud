package com.a_cobra.aws_learning.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
  @Value("${aws.endpoint}")
  private String awsEndpoint;

  @Value("${aws.dynamo.credentials.id}")
  private String dynamoId;

  @Value("${aws.dynamo.credentials.secret}")
  private String dynamoSecret;

  private URI awsEndpointUri;

  private DynamoDbClient dynamoDbClient() {
    DynamoDbClient dynamoDbClient = DynamoDbClient.builder()
        .endpointOverride(
            awsEndpointUri)
        .region(Region.SA_EAST_1)
        .credentialsProvider(StaticCredentialsProvider.create(
            AwsBasicCredentials.create(
                dynamoId,
                dynamoSecret)))
        .build();
    return dynamoDbClient;
  }

  @Bean
  public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
    awsEndpointUri = URI.create(awsEndpoint);
    DynamoDbEnhancedClient dynamoDbEnhancedClient = DynamoDbEnhancedClient.builder()
        .dynamoDbClient(this
            .dynamoDbClient())
        .build();
    return dynamoDbEnhancedClient;
  }
}
