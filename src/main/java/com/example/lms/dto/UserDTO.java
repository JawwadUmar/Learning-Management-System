package com.example.lms.dto;

import com.example.lms.model.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {
  private long userId;
  private String name;
  private String email;
  private String profilePic;
  private Role role;
  private String phoneNumber;
}
