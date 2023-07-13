package com.shopspace.shopspaceadminservice.service;

import com.shopspace.shopspaceadminservice.dto.AuthRequestDTO;
import com.shopspace.shopspaceadminservice.dto.AuthResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthResponseDTO authenticate(HttpServletRequest request, AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(HttpServletRequest request);

    Boolean logout(HttpServletRequest request);
}
