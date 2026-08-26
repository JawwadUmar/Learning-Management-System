package com.example.lms.exception;

import com.example.lms.dto.ApiResponse;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(
      BadCredentialsException ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleUsernameNotFoundException(
      UsernameNotFoundException ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<ApiResponse<Object>> handleInvalidCredentialsException(
      InvalidCredentialsException ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }

  @ExceptionHandler(InvalidRequestException.class)
  public ResponseEntity<ApiResponse<Object>> handleInvalidRequestException(
      InvalidRequestException ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ApiResponse<Object>> handleUserAlreadyExistsException(
      UserAlreadyExistsException ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    String errorMessage =
        ex.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(", "));
    ApiResponse<Object> response = new ApiResponse<>(false, errorMessage, null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiResponse<Object>> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    ApiResponse<Object> response =
        new ApiResponse<>(false, "Required request body is missing or malformed", null);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
    ApiResponse<Object> response = new ApiResponse<>(false, ex.getMessage(), null);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }
}
