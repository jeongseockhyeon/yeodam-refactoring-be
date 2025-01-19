package com.example.yeodamrefactoring.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDto {

    private String email;

    private String password;

    public LoginReqDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
