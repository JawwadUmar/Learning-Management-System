package com.example.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Teacher extends User{
    @PrePersist
    private void setRole(){
        setRole(Role.TEACHER);
    }

}
