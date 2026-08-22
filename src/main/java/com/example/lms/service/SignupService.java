package com.example.lms.service;

import com.example.lms.dto.SignupRequest;
import com.example.lms.exception.UserAlreadyExistsException;
import com.example.lms.model.Admin;
import com.example.lms.model.Student;
import com.example.lms.model.Teacher;
import com.example.lms.repository.AdminRepository;
import com.example.lms.repository.StudentRepository;
import com.example.lms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignupService {
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;
  private final AdminRepository adminRepository;
  private final PasswordEncoder passwordEncoder;

  public void handleSignup(SignupRequest signupRequest) {

    boolean emailExists =
        switch (signupRequest.getRole()) {
          case STUDENT -> studentRepository.existsByEmail(signupRequest.getEmail());
          case TEACHER -> teacherRepository.existsByEmail(signupRequest.getEmail());
          case ADMIN -> adminRepository.existsByEmail(signupRequest.getEmail());
        };

    if (emailExists) {
      throw new UserAlreadyExistsException("User already exists with this email");
    }

    String hashedPassword = passwordEncoder.encode(signupRequest.getPassword());

    switch (signupRequest.getRole()) {
      case STUDENT ->
          studentRepository.save(
              Student.builder()
                  .email(signupRequest.getEmail())
                  .password(hashedPassword)
                  .name(signupRequest.getName())
                  .role(signupRequest.getRole())
                  .phoneNumber(signupRequest.getPhoneNumber())
                  .build());

      case TEACHER ->
          teacherRepository.save(
              Teacher.builder()
                  .email(signupRequest.getEmail())
                  .password(hashedPassword)
                  .name(signupRequest.getName())
                  .role(signupRequest.getRole())
                  .phoneNumber(signupRequest.getPhoneNumber())
                  .build());

      case ADMIN ->
          adminRepository.save(
              Admin.builder()
                  .email(signupRequest.getEmail())
                  .password(hashedPassword)
                  .name(signupRequest.getName())
                  .role(signupRequest.getRole())
                  .phoneNumber(signupRequest.getPhoneNumber())
                  .build());
    }
  }
}
