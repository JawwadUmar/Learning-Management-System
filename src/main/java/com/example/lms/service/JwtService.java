package com.example.lms.service;

import com.example.lms.configuration.CustomUserDetails;
import com.example.lms.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtService {

  @Value("${jwt.secret}")
  private final String secret;

  @Value("${jwt.expiration}")
  private final long expiration;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String generateToken(CustomUserDetails userDetails, Role role) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", role.name());
    claims.put("email", userDetails.getEmail());
    claims.put("phone", userDetails.getPhoneNumber());
    claims.put("user_id", String.valueOf(userDetails.getId()));

    return Jwts.builder()
        .subject(String.valueOf(userDetails.getId()))
        .claims(claims)
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(getSigningKey())
        .compact();
  }

  public boolean isTokenValid(String token, CustomUserDetails userDetails) {
    String userId = extractValueFromClaims(token, "user_id");
    return !isTokenExpired(token) && userId.equals(String.valueOf(userDetails.getId()));
  }

  public boolean isTokenExpired(String token) {
    Claims claims = extractAllClaims(token);
    return claims.getExpiration().before(new Date());
  }

  public String extractValueFromClaims(String token, String key) {
    Claims claims = extractAllClaims(token);
    return claims.get(key, String.class);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
  }
}
