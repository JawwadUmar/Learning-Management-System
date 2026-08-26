package com.example.lms.service;

import com.example.lms.configuration.CustomUserDetails;
import com.example.lms.dto.AuthenticationResponse;
import com.example.lms.dto.SigninRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final CustomUserDetailsService customUserDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthenticationResponse authenticate(SigninRequest signinRequest) {
    CustomUserDetails userDetails =
        customUserDetailsService.loadUserByUsername(
            signinRequest.getEmail(), signinRequest.getPhoneNumber(), signinRequest.getRole());

    if (!passwordEncoder.matches(signinRequest.getPassword(), userDetails.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }

    String jwtToken = jwtService.generateToken(userDetails);

    return AuthenticationResponse.builder().jwtToken(jwtToken).userDetails(userDetails).build();
  }
}
