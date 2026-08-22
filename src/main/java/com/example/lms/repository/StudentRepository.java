package com.example.lms.repository;

import com.example.lms.model.Admin;
import com.example.lms.model.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  boolean existsByEmail(String email);

  Optional<Admin> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
