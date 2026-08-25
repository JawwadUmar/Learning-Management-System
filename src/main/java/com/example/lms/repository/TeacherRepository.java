package com.example.lms.repository;

import com.example.lms.model.Teacher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
  boolean existsByEmail(String email);

  Optional<Teacher> findByEmailOrPhoneNumber(String email, String phoneNumber);
}
