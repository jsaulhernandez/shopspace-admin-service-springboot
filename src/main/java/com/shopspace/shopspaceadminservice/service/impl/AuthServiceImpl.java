package com.shopspace.shopspaceadminservice.service.impl;

import com.shopspace.shopspaceadminservice.client.UserAdminClient;
import com.shopspace.shopspaceadminservice.config.JwtAuthenticationFilter;
import com.shopspace.shopspaceadminservice.dto.AuthRequestDTO;
import com.shopspace.shopspaceadminservice.dto.AuthResponseDTO;
import com.shopspace.shopspaceadminservice.dto.UserAdminDTO;
import com.shopspace.shopspaceadminservice.exception.DataNotFoundException;
import com.shopspace.shopspaceadminservice.exception.MessagesExceptions;
import com.shopspace.shopspaceadminservice.exception.InvalidTokenException;
import com.shopspace.shopspaceadminservice.service.AuthService;
import com.shopspace.shopspaceadminservice.service.JwtService;
import com.shopspace.shopspaceadminservice.util.ShopSpaceAdminUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserAdminClient userAdminClient;
    @Autowired
    JwtService jwtService;

    @Override
    public AuthResponseDTO authenticate(HttpServletRequest request, AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));

        var user = userAdminClient.getOneUserAdminByEmail(authRequestDTO.getEmail()).orElseThrow(()-> new DataNotFoundException("User with email " + authRequestDTO.getEmail() + " does not exists."));

        return generateUserData(request, user);
    }

    @Override
    public AuthResponseDTO refreshToken(HttpServletRequest request){
        try {
            final Optional<String> oldToken = jwtService.getJwtFromRequest(request);

            if (oldToken.isEmpty()) throw new InvalidTokenException(MessagesExceptions.INVALID_TOKEN);

            String userName = jwtService.extractUserName(oldToken.get());

            UserAdminDTO user = userAdminClient.getOneUserAdminByEmail(userName).orElseThrow(() -> new DataNotFoundException("User with email " + userName + " does not exists."));

            boolean isValidateToken = jwtService.isTokenValid(oldToken.get(), user);

            if (!isValidateToken) throw new BadCredentialsException(MessagesExceptions.BAD_CREDENTIALS);

            final String ipAddress = ShopSpaceAdminUtil.getClientIp(request);
            final String userAgent = ShopSpaceAdminUtil.getUserAgent(request);
            final String ipAddressByToken = jwtService.getPropertyByToken(oldToken.get(), "ip");
            final String userAgentByToken = jwtService.getPropertyByToken(oldToken.get(), "ua");

            if(!ipAddress.contentEquals(ipAddressByToken) || !userAgent.contentEquals(userAgentByToken)) throw new BadCredentialsException(MessagesExceptions.BAD_CREDENTIALS);

            return generateUserData(request, user);
        } catch (IllegalArgumentException | MalformedJwtException | ExpiredJwtException e) {
            log.error("Error in refreshToken {}", e.getMessage());
            throw new InvalidTokenException(MessagesExceptions.EXCEPTION_TOKEN);
        }
    }

    @Override
    public Boolean logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        return true;
    }

    public AuthResponseDTO generateUserData(HttpServletRequest request, UserAdminDTO user){
        final Map<String, Object> claims = new HashMap<>();
        claims.put("ip", ShopSpaceAdminUtil.getClientIp(request));
        claims.put("us", ShopSpaceAdminUtil.getUserAgent(request));

        var jwt = jwtService.generateToken(claims, user);
        var expiration = jwtService.getExpirationToken(jwt);

        return new AuthResponseDTO(user, jwt, expiration);
    }
}
