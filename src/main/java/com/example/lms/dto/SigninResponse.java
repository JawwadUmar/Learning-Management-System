package com.example.lms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninResponse {
  private String jwtToken;
  private Long expiresIn;
  private UserDTO userDTO;
}
