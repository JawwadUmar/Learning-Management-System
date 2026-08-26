package com.example.lms.service;

import com.example.lms.configuration.CustomUserDetails;
import com.example.lms.model.Admin;
import com.example.lms.model.Role;
import com.example.lms.model.Student;
import com.example.lms.model.Teacher;
import com.example.lms.repository.AdminRepository;
import com.example.lms.repository.StudentRepository;
import com.example.lms.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService {

  private final AdminRepository adminRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  public CustomUserDetails loadUserByUsername(String email, String phoneNumber, Role role)
      throws UsernameNotFoundException {

    return switch (role) {
      case TEACHER -> loadTeacherByUserName(email, phoneNumber);
      case ADMIN -> loadAdminByUserName(email, phoneNumber);
      case STUDENT -> loadStudentByUserName(email, phoneNumber);
    };
  }

  private CustomUserDetails loadTeacherByUserName(String email, String phoneNumber) {
    Teacher teacher =
        teacherRepository
            .findByEmailAndPhoneNumber(email, phoneNumber)
            .orElseThrow(() -> new UsernameNotFoundException("Teacher with this id doesn't exist"));
    return new CustomUserDetails(teacher);
  }

  private CustomUserDetails loadAdminByUserName(String email, String phoneNumber) {
    Admin admin =
        adminRepository
            .findByEmailAndPhoneNumber(email, phoneNumber)
            .orElseThrow(() -> new UsernameNotFoundException("Admin with this id doesn't exist"));
    return new CustomUserDetails(admin);
  }

  private CustomUserDetails loadStudentByUserName(String email, String phoneNumber) {
    Student student =
        studentRepository
            .findByEmailAndPhoneNumber(email, phoneNumber)
            .orElseThrow(() -> new UsernameNotFoundException("Student with this id doesnt exist"));
    return new CustomUserDetails(student);
  }
}
