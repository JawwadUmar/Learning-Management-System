package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private int teacherId;
    private String enrollmentDate;
}
