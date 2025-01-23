package com.example.yeodamrefactoring.auth.dto;

import lombok.Getter;

@Getter
public class ResTokenDto {
    private final String accessToken;
    private final String refreshToken;

    public ResTokenDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
