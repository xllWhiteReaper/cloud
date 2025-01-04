package com.a_cobra.aws_learning.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a_cobra.aws_learning.dto.CreateUserDto;
import com.a_cobra.aws_learning.entities.User;
import com.a_cobra.aws_learning.services.UsersService;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UsersController {
  @Autowired
  private UsersService usersService;

  @GetMapping()
  public ResponseEntity<List<User>> findAll() {
    return this.usersService.findAll();
  }

  @PostMapping()
  public ResponseEntity<String> createOne(@RequestBody() CreateUserDto createUserDto) {
    return this.usersService.createOne(createUserDto);
  }
}
