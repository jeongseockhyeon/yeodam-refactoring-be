package com.example.yeodamrefactoring.seller.repository;

import com.example.yeodamrefactoring.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
