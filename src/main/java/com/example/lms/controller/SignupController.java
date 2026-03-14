package com.example.lms.controller;

import com.example.lms.dto.SignupRequest;
import com.example.lms.dto.SignupResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SignupController {

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SignupResponse> signup(@ModelAttribute SignupRequest signUpRequest, @RequestParam(value = "profilePic", required = false) MultipartFile profilePic){
        return ResponseEntity.ok().body(new SignupResponse("Not found"));
    }
}
