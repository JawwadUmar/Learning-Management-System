package com.example.lms.controller;

import com.example.lms.dto.ApiResponse;
import com.example.lms.dto.SignupRequest;
import com.example.lms.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignupController {

  private final SignupService signupService;

  @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse<SignupRequest>> signup(
      @Valid @ModelAttribute SignupRequest signUpRequest) {
    signupService.handleSignup(signUpRequest);
    ApiResponse<SignupRequest> response =
        new ApiResponse<>(true, "Sign up successful", signUpRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
