package com.a_cobra.aws_learning.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.a_cobra.aws_learning.dto.CreateUserDto;
import com.a_cobra.aws_learning.entities.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;

@Service
public class UsersService {
  private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
  private final DynamoDbTable<User> usersTable;

  public UsersService(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
    this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    usersTable = this.dynamoDbEnhancedClient.table("cms_users",
        TableSchema.fromBean(User.class));
  }

  public ResponseEntity<String> createOne(CreateUserDto createUserDto) {
    User userToCreate = new User();
    userToCreate.setAddress(createUserDto.getAddress());
    userToCreate.setLast_name(createUserDto.getLast_name());
    userToCreate.setFirst_name(createUserDto.getFirst_name());
    userToCreate.setEmail(createUserDto.getEmail());
    // String id =

    PutItemEnhancedRequest<User> enhancedRequest = PutItemEnhancedRequest.builder(User.class)
        .item(userToCreate)
        .build();
    try {
      this.usersTable.putItem(enhancedRequest);
      return ResponseEntity.ok().body("User created successfully");
    } catch (Exception e) {
      final String errorMessage = "Error: " + e.getMessage();
      System.out.println(errorMessage);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
  }

  public ResponseEntity<List<User>> findAll() {
    try {
      ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
          .limit(100)
          .build();

      Iterator<User> results = this.usersTable.scan(scanRequest).items().iterator();

      List<User> users = new ArrayList<>();
      while (results.hasNext()) {
        User foundUser = results.next();
        users.add(foundUser);
      }

      return ResponseEntity.ok(users);
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      // Return an empty list if an error occurs
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
    }
  }
}
