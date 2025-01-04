package com.a_cobra.aws_learning.dto;

import com.a_cobra.aws_learning.entities.Address;

import lombok.Data;

@Data
public class CreateUserDto {
  private String first_name;
  private String last_name;
  private String email;
  private Address address;
}
