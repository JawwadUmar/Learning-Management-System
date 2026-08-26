package com.example.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddCourseRequest {
  private String courseName;
  private String curriculum;
  private String courseLevel;
}
