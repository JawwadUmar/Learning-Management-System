package com.example.lms.repository;

import com.example.lms.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  public boolean existsByEmail(String email);
}
