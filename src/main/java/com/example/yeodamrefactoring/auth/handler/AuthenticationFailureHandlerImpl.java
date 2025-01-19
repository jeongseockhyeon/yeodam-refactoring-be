package com.example.yeodamrefactoring.auth.handler;

import com.example.yeodamrefactoring.global.common.ApiResponse;
import com.example.yeodamrefactoring.global.common.ApiResponseType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        ApiResponse.error(response, ApiResponseType.UNAUTHORIZED);
    }
}
