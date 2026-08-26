package com.example.lms.repository;

import com.example.lms.model.Teacher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  boolean existsByEmail(String email);

  Optional<Teacher> findByEmail(String email);

  Optional<Teacher> findByPhoneNumber(String phoneNumber);

  default Optional<Teacher> findByEmailOrPhoneNumber(String email, String phoneNumber) {
    if (email != null && !email.isBlank()) {
      return findByEmail(email);
    }
    if (phoneNumber != null && !phoneNumber.isBlank()) {
      return findByPhoneNumber(phoneNumber);
    }
    return Optional.empty();
  }
}
