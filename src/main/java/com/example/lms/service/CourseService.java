package com.example.lms.service;

import com.example.lms.dto.AddCourseRequest;
import com.example.lms.model.Course;
import com.example.lms.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {
  private final CourseRepository courseRepository;

  public void addCourse(AddCourseRequest addCourseRequest) {
    Course course = getCourseEntityFromDTO(addCourseRequest);
    courseRepository.save(course);
  }

  private Course getCourseEntityFromDTO(AddCourseRequest addCourseRequest) {
    return Course.builder()
        .courseName(addCourseRequest.getCourseName())
        .courseLevel(addCourseRequest.getCourseLevel())
        .curriculum(addCourseRequest.getCurriculum())
        .build();
  }
}
