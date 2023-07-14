package com.shopspace.shopspaceadminservice.controller;

import com.shopspace.shopspaceadminservice.dto.AuthRequestDTO;
import com.shopspace.shopspaceadminservice.dto.response.ResponseDTO;
import com.shopspace.shopspaceadminservice.service.AuthService;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> authenticate(@RequestBody AuthRequestDTO authRequestDTO, HttpServletRequest request){
        return ResponseUtil.ok(authService.authenticate(request, authRequestDTO));
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<ResponseDTO> refreshToken(HttpServletRequest request) {
        return ResponseUtil.ok(authService.refreshToken(request));
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(HttpServletRequest request) {
        return ResponseUtil.ok(authService.logout(request));
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
