package com.backend.pfe.service;

import com.backend.pfe.entites.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    String generateToken(UserDetails userDetails);
    String generateRefreshToken(Map<String, Object> extraClaims, User user);
    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
