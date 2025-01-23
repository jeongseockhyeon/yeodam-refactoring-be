package com.example.yeodamrefactoring.seller.entity;

import com.example.yeodamrefactoring.auth.entity.Auth;
import com.example.yeodamrefactoring.auth.entity.type.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("seller")
@AllArgsConstructor
public class Seller extends Auth {

    private String companyName;

    private String owner;

    private String bio;

    private String phone;

    @Builder
    public Seller(String email,
                  String password,
                  Role role,
                  String companyName,
                  String owner,
                  String bio,
                  String phone) {

        super(email, password, role);
        this.companyName = companyName;
        this.owner = owner;
        this.bio = bio;
        this.phone = phone;
    }
}
