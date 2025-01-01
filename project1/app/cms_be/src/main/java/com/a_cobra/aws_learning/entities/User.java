package com.a_cobra.aws_learning.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDbBean
public class User implements Serializable {
  private String id;
  private String first_name;
  private String last_name;
  private String email;
  private Address address;

  @DynamoDbPartitionKey
  public void setId(String id) {
    this.id = id;
  }
}
