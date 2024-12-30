package com.a_cobra.aws_learning.entities;

import java.io.Serializable;
// import software.amazon.adsk.enhance

import lombok.Data;

// @DynamoDBDocument()
@Data
public class Address implements Serializable {
  private String country;
  private String region;
  private String city;
  private String direction;
  private int zip_code;
}
