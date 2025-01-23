package com.example.yeodamrefactoring.user.service;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import com.example.yeodamrefactoring.user.entity.User;
import com.example.yeodamrefactoring.user.dto.JoinReqDto;
import com.example.yeodamrefactoring.user.dto.UserResDto;
import com.example.yeodamrefactoring.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResDto join(JoinReqDto joinReqDto){

        User user = User.builder()
                .email(joinReqDto.getEmail())
                .password(passwordEncoder.encode(joinReqDto.getPassword()))
                .role(Role.USER)
                .name(joinReqDto.getName())
                .phone(joinReqDto.getPhone())
                .birthDate(joinReqDto.getBirthDate())
                .gender(joinReqDto.getGender())
                .nickname(joinReqDto.getNickname())
                .build();

        User savedUser = userRepository.save(user);

        return new UserResDto(savedUser);
    }
}
