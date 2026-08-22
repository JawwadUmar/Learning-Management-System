package com.example.lms.dto;

import com.example.lms.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SigninRequest {

  @Email(message = "Invalid email format")
  private String email;

  @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
  private String phoneNumber;

  @NotNull(message = "Role is required")
  private Role role;

  @NotBlank(message = "Password is required")
  private String password;
}
