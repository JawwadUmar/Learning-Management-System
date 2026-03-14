package com.example.lms.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class Teacher extends User{
    Teacher(String name, long userId, String email, String password, String phoneNumber, String profilePic, String googleId) {
        super(name, userId, email, password, phoneNumber, profilePic, googleId);
    }
}
