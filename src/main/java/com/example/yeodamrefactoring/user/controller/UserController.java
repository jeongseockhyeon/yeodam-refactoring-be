package com.example.yeodamrefactoring.user.controller;

import com.example.yeodamrefactoring.user.dto.JoinReqDto;
import com.example.yeodamrefactoring.user.dto.UserResDto;
import com.example.yeodamrefactoring.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v1/users/join")
    public ResponseEntity<UserResDto> join(@RequestBody JoinReqDto joinReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.join(joinReqDto));
    }
}
