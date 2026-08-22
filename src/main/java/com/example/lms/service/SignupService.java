package com.example.lms.service;

import com.example.lms.dto.SignupRequest;
import com.example.lms.exception.UserAlreadyExistsException;
import com.example.lms.model.Student;
import com.example.lms.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
  private final StudentRepository studentRepository;

  public SignupService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void handleSignup(SignupRequest signupRequest) {
    if (studentRepository.existsByEmail(signupRequest.getEmail())) {
      throw new UserAlreadyExistsException("User already exists with this email");
    }
    Student user = new Student();
    user.setEmail(signupRequest.getEmail());
    user.setName(signupRequest.getName());
    user.setPhoneNumber(signupRequest.getPhoneNumber());
    user.setPassword(signupRequest.getPassword());

    studentRepository.save(user);
  }
}
