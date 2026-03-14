package com.example.lms.controller;

import com.example.lms.dto.SignupRequest;
import com.example.lms.dto.SignupResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<SignupResponse> signup(@ModelAttribute SignupRequest signUpRequest){
        return ResponseEntity.status(404).body(new SignupResponse("Not found"));
    }
}
