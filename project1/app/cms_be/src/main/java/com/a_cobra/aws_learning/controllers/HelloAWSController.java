package com.a_cobra.aws_learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a_cobra.aws_learning.services.HelloAWSService;

@RestController()
@RequestMapping(path = "hello-aws")
public class HelloAWSController {
  @Autowired
  private HelloAWSService helloAWSService;

  @GetMapping()
  public String getHelloAWS() {
    return this.helloAWSService.getHelloAWS();
  }
}