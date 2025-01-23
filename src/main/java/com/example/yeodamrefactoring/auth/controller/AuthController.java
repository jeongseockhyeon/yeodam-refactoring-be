package com.example.yeodamrefactoring.auth.controller;

import com.example.yeodamrefactoring.auth.dto.LoginReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthController {

    @PostMapping("/api/v2/auths/login")
    public ResponseEntity<Void> login(@RequestBody LoginReqDto loginReqDto) { return null; }
}
