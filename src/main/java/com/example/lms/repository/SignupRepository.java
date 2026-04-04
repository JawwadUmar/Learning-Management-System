package com.example.lms.repository;

import com.example.lms.model.Student;
import com.example.lms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SignupRepository extends JpaRepository<Student, Long> {
    public boolean existsByEmail(String email);
}
