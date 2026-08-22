package com.example.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Admin extends User {
  @PrePersist
  private void setRole() {
    setRole(Role.ADMIN);
  }
}
