package com.example.yeodamrefactoring.auth.filter;

import com.example.yeodamrefactoring.auth.service.AuthService;
import com.example.yeodamrefactoring.global.common.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLogoutFilter extends GenericFilterBean {

    private final AuthService authService;
    private final ObjectMapper objectMapper;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String COOKIE_NAME = "refresh-token";
    private static final String SET_COOKIE = "Set-Cookie";



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        doFilter((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse, filterChain);
    }

    protected void doFilter(HttpServletRequest request, HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if(!requestURI.matches("^/api/v1/auths/logout$")) {
            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        if(!requestMethod.equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(COOKIE_NAME)) {
                refreshToken = cookie.getValue();
            }
        }

        String accessToken = request.getHeader(AUTHORIZATION_HEADER);

        authService.deleteToken(accessToken);

        response.addHeader(SET_COOKIE, createCookie(null));
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write(objectMapper.writeValueAsString(
                new ApiResponse(200,"logout")
        ));
    }

    private String createCookie(String value) {
        return "refresh-token" + "=" + value + "; Max-Age=7776000; Secure; Path=/; HttpOnly; SameSite=None";
    }
}
