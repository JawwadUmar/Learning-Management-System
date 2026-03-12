package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;


@EqualsAndHashCode(callSuper = true)
@Component  // This makes Spring manage it as a singleton bean
@Entity
public class Admin extends User{
    Admin(String name, int userId, String email, String password, String phoneNumber, String profilePic, String googleId) {
        super(name, userId, email, password, phoneNumber, profilePic, googleId);
    }
}
