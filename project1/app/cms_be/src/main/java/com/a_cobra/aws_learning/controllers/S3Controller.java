package com.a_cobra.aws_learning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.a_cobra.aws_learning.services.S3Service;

@RestController
@RequestMapping(path = "/s3/files")
public class S3Controller {
  @Autowired
  S3Service s3Service;

  @PutMapping()
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    return this.s3Service.uploadFile(file);
  }

  @GetMapping(path = "/{fileName}")
  public ResponseEntity<byte[]> findFile(@PathVariable String fileName) {
    return this.s3Service.findFile(fileName);
  }
}
