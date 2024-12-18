package com.example.e_commerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthCheck(){
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}

