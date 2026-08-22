package com.example.lms.dto;

import com.example.lms.model.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignupRequest {
  @NotBlank(message = "Name is required")
  private String name;

  @Email(message = "Invalid email format")
  private String email;

  @Size(min = 6, message = "Password must be at least 6 characters")
  @NotBlank(message = "Password must not blank")
  private String password;

  @NotBlank(message = "Phone Number is required")
  @Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
  private String phoneNumber;

  @NotNull(message = "Role is required")
  private Role role;
}
