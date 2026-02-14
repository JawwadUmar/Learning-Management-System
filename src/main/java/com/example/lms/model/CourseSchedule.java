package com.example.lms.model;

import lombok.Data;

@Data
public class CourseSchedule {
    private int courseId;
    private int teacherId;
    private int dayId;
    private String courseTiming;
}
