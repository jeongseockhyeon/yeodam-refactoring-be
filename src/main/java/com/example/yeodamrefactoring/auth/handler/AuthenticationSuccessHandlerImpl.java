package com.example.yeodamrefactoring.auth.handler;

import com.example.yeodamrefactoring.auth.dto.ResTokenDto;
import com.example.yeodamrefactoring.auth.jwt.JwtProvider;
import com.example.yeodamrefactoring.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final ResTokenDto tokens = authService.generateToken(authentication);

        response.setHeader("Authorization", "Bearer " + tokens.getAccessToken());
        response.setHeader("Set-Cookie",createCookie(tokens.getRefreshToken()));

        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    }

    private String createCookie(String value) {
        return "refresh-token" + "=" + value + "; Max-Age=7776000; Secure; Path=/; HttpOnly; SameSite=None";
    }
}
