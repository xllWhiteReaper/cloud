package com.a_cobra.aws_learning.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.a_cobra.aws_learning.dto.CreateUserDto;
import com.a_cobra.aws_learning.entities.User;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest;
import software.amazon.awssdk.enhanced.dynamodb.model.ScanEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.ItemResponse;

public class UsersService {
  @Autowired
  private DynamoDbEnhancedClient dynamoDbEnhancedClient;

  private final DynamoDbTable<User> usersTable = this.dynamoDbEnhancedClient.table("User",
      TableSchema.fromBean(User.class));

  public ResponseEntity<String> createUSer(CreateUserDto createUserDto) {
    User userToCreate = new User();
    userToCreate.setAddress(createUserDto.getAddress());
    userToCreate.setLast_name(createUserDto.getLast_name());
    userToCreate.setFirst_name(createUserDto.getFirst_name());
    userToCreate.setEmail(createUserDto.getEmail());

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

  public List<User> findAll() {
    try {
      ScanEnhancedRequest scanRequest = ScanEnhancedRequest.builder()
          .limit(100) // You can adjust this value based on your needs
          .build();

      Iterator<Page<User>> results = this.usersTable.scan(scanRequest).iterator();

      List<User> users = new ArrayList<>();
      // while (results.hasNext()) {
      // Page<User> itemResponse = results.next();
      // users.add(itemResponse.);
      // }

      return users;
    } catch (Exception e) {
      System.out.println("Error: " + e.getMessage());
      return Collections.emptyList(); // Return an empty list if an error occurs
    }
  }
}
