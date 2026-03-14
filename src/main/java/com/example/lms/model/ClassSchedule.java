package com.example.lms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long classId;
    private long courseId;
    private long teacherId;
    private long studentId;
    private long duration; //duration will be in hours
    private String courseTiming;
    private boolean done;
}
