package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Course {
    private int courseId;
    private String courseName;
    private String curriculum;
    private String courseLevel;
}
