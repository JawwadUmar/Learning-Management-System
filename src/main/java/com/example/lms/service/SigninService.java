package com.example.lms.service;

import com.example.lms.dto.SigninRequest;
import com.example.lms.dto.SigninResponse;
import com.example.lms.dto.UserDTO;
import com.example.lms.exception.InvalidCredentialsException;
import com.example.lms.exception.InvalidRequestException;
import com.example.lms.model.User;
import com.example.lms.repository.AdminRepository;
import com.example.lms.repository.StudentRepository;
import com.example.lms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SigninService {
  private final TeacherRepository teacherRepository;
  private final StudentRepository studentRepository;
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  private static final String INVALID_CREDENTIALS_MESSAGE = "Invalid Credentials";

  public SigninResponse handleSignin(SigninRequest signinRequest) {
    if (signinRequest.getEmail() == null && signinRequest.getPhoneNumber() == null) {
      throw new InvalidRequestException("Either email or phone number is required");
    }
    User user =
        switch (signinRequest.getRole()) {
          case ADMIN ->
              adminRepository
                  .findByEmailOrPhoneNumber(
                      signinRequest.getEmail(), signinRequest.getPhoneNumber())
                  .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE));
          case TEACHER ->
              teacherRepository
                  .findByEmailOrPhoneNumber(
                      signinRequest.getEmail(), signinRequest.getPhoneNumber())
                  .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE));
          case STUDENT ->
              studentRepository
                  .findByEmailOrPhoneNumber(
                      signinRequest.getEmail(), signinRequest.getPhoneNumber())
                  .orElseThrow(() -> new InvalidCredentialsException(INVALID_CREDENTIALS_MESSAGE));
        };

    boolean passwordMatches =
        passwordEncoder.matches(signinRequest.getPassword(), user.getPassword());

    if (!passwordMatches) {
      throw new InvalidCredentialsException("Invalid Password");
    }

    UserDTO userDTO = convertToUserDTO(user);

    return SigninResponse.builder()
        .userDTO(userDTO)
        .jwtToken("sample_jwt")
        .expiresIn((long) 3000)
        .build();
  }

  private UserDTO convertToUserDTO(User user) {
    return UserDTO.builder()
        .userId(user.getUserId())
        .email(user.getEmail())
        .name(user.getName())
        .role(user.getRole())
        .profilePic(user.getProfilePic())
        .phoneNumber(user.getPhoneNumber())
        .build();
  }
}
