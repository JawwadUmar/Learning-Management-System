package com.example.lms.repository;

import com.example.lms.model.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  boolean existsByEmail(String email);

  Optional<Admin> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
