package com.shopspace.shopspaceadminservice.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface JwtService {
    String extractUserName(String token);

    String generateToken(Map<String, Object> claims, UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    String getExpirationToken(String jwt);

    Optional<String> getJwtFromRequest(HttpServletRequest request);

    String getPropertyByToken(String jwt, String property);
}
