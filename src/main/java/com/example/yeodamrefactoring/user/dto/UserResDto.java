package com.example.yeodamrefactoring.user.dto;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import com.example.yeodamrefactoring.user.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResDto {

    private final String email;

    private final String name;

    private final LocalDate birthDate;

    private final String nickname;

    private final String gender;

    private final String phone;

    private final Role role;

    public UserResDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.nickname = user.getNickname();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}
