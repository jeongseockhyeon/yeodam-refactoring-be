package com.example.yeodamrefactoring.user.entity;

import com.example.yeodamrefactoring.auth.entity.Auth;
import com.example.yeodamrefactoring.auth.entity.type.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("user")
@Entity
public class User extends Auth {

    private String name;

    private LocalDate birthDate;

    private String nickname;

    private String gender;

    private String phone;

    @Builder
    public User(String email, String password, Role role, String name, LocalDate birthDate, String nickname, String gender, String phone) {
        super(email, password, role);
        this.name = name;
        this.birthDate = birthDate;
        this.nickname = nickname;
        this.gender = gender;
        this.phone = phone;
    }
}
