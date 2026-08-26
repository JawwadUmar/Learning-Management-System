package com.example.lms.controller;

import com.example.lms.dto.AddCourseRequest;
import com.example.lms.dto.ApiResponse;
import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/admin"))
public class CourseController {
  private final CourseService courseService;

  @PostMapping(value = "/course", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ApiResponse<Object>> addCourse(
      @RequestBody AddCourseRequest addCourseRequest) {
    courseService.addCourse(addCourseRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<>(true, "Course added successfully", null));
  }
}
