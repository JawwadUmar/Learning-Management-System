package com.example.lms.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class CourseSchedule {
    private int courseId;
    private int teacherId;
    private int dayId;
    private String courseTiming;
}
