package com.example.yeodamrefactoring.seller.controller;

import com.example.yeodamrefactoring.seller.dto.SellerJoinReqDto;
import com.example.yeodamrefactoring.seller.dto.SellerResDto;
import com.example.yeodamrefactoring.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/api/v1/sellers/join")
    public ResponseEntity<SellerResDto> join(@RequestBody SellerJoinReqDto sellerJoinReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sellerService.join(sellerJoinReqDto));
    }

}
