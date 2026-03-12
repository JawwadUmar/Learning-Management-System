package com.example.lms.dto;

import lombok.Data;

@Data
public class SignupResponse {
    private String message;

    public SignupResponse(String message){
        this.message = message;
    }
}
