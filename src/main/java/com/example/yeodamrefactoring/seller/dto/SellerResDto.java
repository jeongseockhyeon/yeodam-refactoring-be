package com.example.yeodamrefactoring.seller.dto;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import com.example.yeodamrefactoring.seller.entity.Seller;
import lombok.Getter;

@Getter
public class SellerResDto {

    private String email;

    private String phone;

    private String companyName;

    private String owner;

    private String bio;

    private Role role;

    public SellerResDto(Seller seller) {

        this.email = seller.getEmail();
        this.phone = seller.getPhone();
        this.companyName = seller.getCompanyName();
        this.owner = seller.getOwner();
        this.bio = seller.getBio();
        this.role = seller.getRole();

    }


}
