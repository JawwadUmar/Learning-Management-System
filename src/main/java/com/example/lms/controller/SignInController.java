package com.example.lms.controller;

import com.example.lms.dto.ApiResponse;
import com.example.lms.dto.SigninRequest;
import com.example.lms.dto.SigninResponse;
import com.example.lms.service.SigninService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignInController {
  private final SigninService signinService;

  @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<SigninResponse>> login(
      @Valid @RequestBody SigninRequest signInRequest) {
    SigninResponse signinResponse = signinService.handleSignin(signInRequest);
    ApiResponse<SigninResponse> signinResponseApiResponse =
        new ApiResponse<>(true, "Logged in successfully", signinResponse);
    return ResponseEntity.status(HttpStatus.OK).body(signinResponseApiResponse);
  }
}
