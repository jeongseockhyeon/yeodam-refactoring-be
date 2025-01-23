package com.example.yeodamrefactoring.auth.entity;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="auth_type")
@Entity
public abstract class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Auth(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
