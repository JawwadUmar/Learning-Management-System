package com.example.lms.service;

import com.example.lms.configuration.CustomUserDetails;
import com.example.lms.dto.AuthenticationResponse;
import com.example.lms.dto.SigninRequest;
import com.example.lms.dto.SigninResponse;
import com.example.lms.dto.UserDTO;
import com.example.lms.exception.InvalidRequestException;
import com.example.lms.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SigninService {
  private final AuthenticationService authenticationService;

  public SigninResponse handleSignin(SigninRequest signinRequest) {
    if (signinRequest.getEmail() == null && signinRequest.getPhoneNumber() == null) {
      throw new InvalidRequestException("Either email or phone number is required");
    }
    AuthenticationResponse authenticationResponse =
        authenticationService.authenticate(signinRequest);
    String jwtToken = authenticationResponse.getJwtToken();
    CustomUserDetails userDetails = authenticationResponse.getUserDetails();
    UserDTO userDTO = convertToUserDTO(userDetails);

    return SigninResponse.builder().userDTO(userDTO).jwtToken(jwtToken).build();
  }

  private UserDTO convertToUserDTO(CustomUserDetails userDetails) {
    return UserDTO.builder()
        .userId(userDetails.getId())
        .email(userDetails.getEmail())
        .name(userDetails.getName())
        .role(Role.valueOf(userDetails.getRole()))
        .phoneNumber(userDetails.getPhoneNumber())
        .profilePic(userDetails.getProfilePic())
        .build();
  }
}
