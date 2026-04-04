package com.example.lms.controller;

import com.example.lms.dto.ApiResponse;
import com.example.lms.dto.SignupRequest;
import com.example.lms.dto.SignupResponse;
import com.example.lms.exception.UserAlreadyExistsException;
import com.example.lms.service.SignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SignupController {
    private final SignupService signupService;

    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping(value = "/signup", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<SignupRequest>> signup(@Valid @ModelAttribute SignupRequest signUpRequest){
        try{
            signupService.handleSignup(signUpRequest);

        } catch (UserAlreadyExistsException e) {
            ApiResponse<SignupRequest> response = new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null
            );

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } catch (Exception e) {
            ApiResponse<SignupRequest> response = new ApiResponse<>(
                    false,
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        ApiResponse<SignupRequest> response = new ApiResponse<>(
                true,
                "Sign up successful",
                signUpRequest
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
