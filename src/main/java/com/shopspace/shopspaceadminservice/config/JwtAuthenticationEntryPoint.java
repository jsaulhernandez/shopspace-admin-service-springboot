package com.shopspace.shopspaceadminservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopspace.shopspaceadminservice.util.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.err.println(authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        var responseDto = ResponseUtil.unauthorized(null).getBody();

        if (request.getAttribute("exception") instanceof Exception) {
            Exception exception = (Exception) request.getAttribute("exception");

            if (responseDto != null) {
                responseDto.setResponse(exception.getMessage());
            }
        } else if (!Objects.isNull(request.getAttribute("message"))) {
            if (responseDto != null) {
                responseDto.setResponse(request.getAttribute("message"));
            }
        } else {
            if (responseDto != null) {
                responseDto.setResponse(authException.getMessage());
            }
        }

        byte[] body = new ObjectMapper().writeValueAsBytes(responseDto);

        response.getOutputStream().write(body);
    }
}
