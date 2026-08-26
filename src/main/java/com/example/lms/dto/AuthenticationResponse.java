package com.example.lms.dto;

import com.example.lms.configuration.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
  String jwtToken;
  CustomUserDetails userDetails;
}
