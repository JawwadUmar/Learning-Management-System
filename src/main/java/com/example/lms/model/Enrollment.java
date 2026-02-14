package com.example.lms.model;

import lombok.Data;

@Data
public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private int courseId;
    private int teacherId;
    private String enrollmentDate;
}
