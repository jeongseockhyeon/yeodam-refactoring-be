package com.example.yeodamrefactoring.auth.service;

import com.example.yeodamrefactoring.auth.dto.ResTokenDto;
import com.example.yeodamrefactoring.auth.jwt.JwtProvider;
import com.example.yeodamrefactoring.global.Dao.RedisDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final RedisDao redisDao;
    private final JwtProvider jwtProvider;

    public ResTokenDto generateToken(Authentication authentication) {
        if(redisDao.getRefreshToken(authentication.getPrincipal().toString()) == null) {
            redisDao.deleteRefreshToken(authentication.getPrincipal().toString());
        }

        ResTokenDto tokenDto = jwtProvider.generateToken(authentication);
        saveRefreshToken(authentication.getPrincipal().toString(), tokenDto.getRefreshToken());
        return tokenDto;
    }

    public void saveRefreshToken(String email,String refreshToken) {
        redisDao.setRefreshToken(
                email,
                refreshToken,
                jwtProvider.getTokenValidTime(refreshToken)
                );
    }
}
