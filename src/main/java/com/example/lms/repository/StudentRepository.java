package com.example.lms.repository;

import com.example.lms.model.Student;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  boolean existsByEmail(String email);

  Optional<Student> findByEmail(String email);

  Optional<Student> findByPhoneNumber(String phoneNumber);

  default Optional<Student> findByEmailOrPhoneNumber(String email, String phoneNumber) {
    if (email != null && !email.isBlank()) {
      return findByEmail(email);
    }
    if (phoneNumber != null && !phoneNumber.isBlank()) {
      return findByPhoneNumber(phoneNumber);
    }
    return Optional.empty();
  }
}
