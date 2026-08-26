package com.example.lms.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.lms.configuration.SecurityConfig;
import com.example.lms.dto.SigninRequest;
import com.example.lms.dto.SigninResponse;
import com.example.lms.dto.UserDTO;
import com.example.lms.exception.GlobalExceptionHandler;
import com.example.lms.exception.InvalidRequestException;
import com.example.lms.model.Role;
import com.example.lms.service.SigninService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = SignInController.class)
@Import({SecurityConfig.class, GlobalExceptionHandler.class})
class SignInControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private SigninService signinService;

  @Test
  void login_withValidJsonPayload_shouldReturnOk() throws Exception {
    SigninRequest request = new SigninRequest();
    request.setEmail("user@example.com");
    request.setPhoneNumber("1234567890");
    request.setRole(Role.STUDENT);
    request.setPassword("password123");

    UserDTO userDTO =
        UserDTO.builder()
            .userId(1L)
            .email("user@example.com")
            .name("Test User")
            .role(Role.STUDENT)
            .phoneNumber("1234567890")
            .build();

    SigninResponse response =
        SigninResponse.builder().jwtToken("mocked-jwt-token").userDTO(userDTO).build();

    when(signinService.handleSignin(any(SigninRequest.class))).thenReturn(response);

    mockMvc
        .perform(
            post("/user/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.message").value("Logged in successfully"))
        .andExpect(jsonPath("$.data.jwtToken").value("mocked-jwt-token"));
  }

  @Test
  void login_withMissingFields_shouldReturn400BadRequest() throws Exception {
    SigninRequest request = new SigninRequest();
    // missing role and password

    mockMvc
        .perform(
            post("/user/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.success").value(false));
  }

  @Test
  void login_withBadCredentials_shouldReturn401Unauthorized() throws Exception {
    SigninRequest request = new SigninRequest();
    request.setEmail("user@example.com");
    request.setPhoneNumber("1234567890");
    request.setRole(Role.STUDENT);
    request.setPassword("wrongpassword");

    when(signinService.handleSignin(any(SigninRequest.class)))
        .thenThrow(new BadCredentialsException("Invalid credentials"));

    mockMvc
        .perform(
            post("/user/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnauthorized())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.message").value("Invalid credentials"));
  }

  @Test
  void login_withInvalidRequest_shouldReturn400BadRequest() throws Exception {
    SigninRequest request = new SigninRequest();
    request.setRole(Role.STUDENT);
    request.setPassword("password123");
    // neither email nor phoneNumber set

    when(signinService.handleSignin(any(SigninRequest.class)))
        .thenThrow(new InvalidRequestException("Either email or phone number is required"));

    mockMvc
        .perform(
            post("/user/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.success").value(false))
        .andExpect(jsonPath("$.message").value("Either email or phone number is required"));
  }
}
