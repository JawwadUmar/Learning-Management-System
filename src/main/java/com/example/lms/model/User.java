package com.example.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private int userId;
    private String email;
    private String password;
    private String phoneNumber;
    private String profilePic;
    private String googleId;
}
