package com.example.yeodamrefactoring.seller.service;

import com.example.yeodamrefactoring.auth.entity.type.Role;
import com.example.yeodamrefactoring.seller.dto.SellerJoinReqDto;
import com.example.yeodamrefactoring.seller.dto.SellerResDto;
import com.example.yeodamrefactoring.seller.entity.Seller;
import com.example.yeodamrefactoring.seller.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerResDto join(SellerJoinReqDto sellerJoinDto) {

        Seller seller = Seller.builder()
                .email(sellerJoinDto.getEmail())
                .password(sellerJoinDto.getPassword())
                .role(Role.SELLER)
                .companyName(sellerJoinDto.getCompanyName())
                .owner(sellerJoinDto.getOwner())
                .phone(sellerJoinDto.getPhone())
                .bio(sellerJoinDto.getBio())
                .build();

        Seller savedSeller = sellerRepository.save(seller);


        return new SellerResDto(savedSeller);
    }

}
