package com.example.lms.service;

import com.example.lms.configuration.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration}")
  private long expiration;

  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String generateToken(CustomUserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", userDetails.getRole());
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
