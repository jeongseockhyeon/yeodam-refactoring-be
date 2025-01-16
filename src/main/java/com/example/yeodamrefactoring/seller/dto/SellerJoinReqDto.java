package com.example.yeodamrefactoring.seller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class SellerJoinReqDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @NotBlank
    @Pattern(regexp = "^\\d{9,11}$")
    private String phone;

    @NotBlank
    @Size(max = 25)
    private String companyName;

    @NotBlank
    @Size(max = 25)
    @Pattern(regexp = "^[a-zA-Z가-힣]+$", message = "이름은 영어 또는 한글로만 입력 가능합니다.")
    private String owner;

    @NotBlank
    private String bio;

    public SellerJoinReqDto(String email,
                            String password,
                            String phone,
                            String companyName,
                            String owner,
                            String bio) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.companyName = companyName;
        this.owner = owner;
        this.bio = bio;

    }
}
