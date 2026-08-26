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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService {

  private final AdminRepository adminRepository;
  private final StudentRepository studentRepository;
  private final TeacherRepository teacherRepository;

  public UserDetails loadUserByUsername(String id, Role role) throws UsernameNotFoundException {

    return switch (role) {
      case TEACHER -> loadTeacherByUserName(id);
      case ADMIN -> loadAdminByUserName(id);
      case STUDENT -> loadStudentByUserName(id);
    };
  }

  private UserDetails loadTeacherByUserName(String id) {
    Teacher teacher =
        teacherRepository
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new UsernameNotFoundException("Teacher with this id doesn't exist"));
    return new CustomUserDetails(teacher);
  }

  private UserDetails loadAdminByUserName(String id) {
    Admin admin =
        adminRepository
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new UsernameNotFoundException("Admin with this id doesn't exist"));
    return new CustomUserDetails(admin);
  }

  private UserDetails loadStudentByUserName(String id) {
    Student student =
        studentRepository
            .findById(Long.valueOf(id))
            .orElseThrow(() -> new UsernameNotFoundException("Student with this id doesnt exist"));
    return new CustomUserDetails(student);
  }
}
