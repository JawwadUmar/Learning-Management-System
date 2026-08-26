package com.example.lms.repository;

import com.example.lms.model.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  boolean existsByEmail(String email);

  Optional<Admin> findByEmail(String email);

  Optional<Admin> findByPhoneNumber(String phoneNumber);

  default Optional<Admin> findByEmailOrPhoneNumber(String email, String phoneNumber) {
    if (email != null && !email.isBlank()) {
      return findByEmail(email);
    }
    if (phoneNumber != null && !phoneNumber.isBlank()) {
      return findByPhoneNumber(phoneNumber);
    }
    return Optional.empty();
  }
}
