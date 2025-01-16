package com.example.yeodamrefactoring.user.repository;

import com.example.yeodamrefactoring.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
