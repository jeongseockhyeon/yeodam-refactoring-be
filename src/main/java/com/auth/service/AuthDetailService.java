package com.auth.service;

import com.auth.dto.AuthDetails;
import com.auth.entity.Auth;
import com.auth.repository.AuthRepository;
import com.example.yeodamrefactoring.global.exception.CustomErrorCode;
import com.example.yeodamrefactoring.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Auth auth = authRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(CustomErrorCode.AUTH_NOT_FOUND));
        return new AuthDetails(auth);
    }
}
