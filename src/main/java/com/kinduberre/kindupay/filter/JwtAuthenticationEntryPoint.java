package com.kinduberre.kindupay.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
// if you want to return 401 status, this is enough.

// if you want to customize your response you can make it as below.
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{ \"type\": \"about:blank\",\n" +
                "    \"title\": \"Unauthorized Access Error\",\n" +
                "    \"status\": 401,\n" +
                "    \"detail\": \"" + authException.getMessage() + "\",\n" +
                "    \"instance\": \"/api/v1/fbl/validation\",\n" +
                "    \"description\": \"Unauthorized Access Error\" }");
    }
}