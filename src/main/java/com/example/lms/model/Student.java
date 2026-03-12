package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Student extends User {

    Student(String name, int userId, String email, String password, String phoneNumber, String profilePic, String googleId) {
        super(name, userId, email, password, phoneNumber, profilePic, googleId);
    }
}
