package com.example.yeodamrefactoring.user.dto;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import com.example.yeodamrefactoring.user.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserResDto {

    private String email;

    private String name;

    private LocalDate birthDate;

    private String nickname;

    private String gender;

    private String phone;

    private Role role;

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
