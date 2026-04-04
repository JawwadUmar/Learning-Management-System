package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Student extends User {

    public Student(String name, long userId, String email, String password, String phoneNumber, String profilePic, String googleId) {
        super(name, userId, email, password, phoneNumber, profilePic, googleId);
    }
}
