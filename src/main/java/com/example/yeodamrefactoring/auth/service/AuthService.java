package com.example.yeodamrefactoring.auth.service;

import com.example.yeodamrefactoring.auth.dto.ResTokenDto;
import com.example.yeodamrefactoring.auth.jwt.JwtProvider;
import com.example.yeodamrefactoring.global.Dao.RedisDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {
    private final RedisDao redisDao;
    private final JwtProvider jwtProvider;

    private final static int subStringNum = 7;



    /*토큰 생성*/
    public ResTokenDto generateToken(Authentication authentication) {
        if(redisDao.getRefreshToken(authentication.getPrincipal().toString()) == null) {
            redisDao.deleteRefreshToken(authentication.getPrincipal().toString());
        }

        ResTokenDto tokenDto = jwtProvider.generateToken(authentication);
        saveRefreshToken(authentication.getPrincipal().toString(), tokenDto.getRefreshToken());
        return tokenDto;
    }

    /*리프레쉬 토큰 레디스에 저장*/
    public void saveRefreshToken(String email,String refreshToken) {
        redisDao.setRefreshToken(
                email,
                refreshToken,
                jwtProvider.getTokenValidTime(refreshToken)
                );
    }

    /*토큰 무효화*/
    public void deleteToken(String accessTokenInHeader) {
        String targetAccessToken = getAccessTokenInHeader(accessTokenInHeader);
        String principal = getPrincipal(targetAccessToken);
        long expiration = jwtProvider.getTokenValidTime(targetAccessToken);

        String refreshTokenInRedis = redisDao.getRefreshToken(principal);
        if(refreshTokenInRedis != null) {
            log.info("로그아웃 시도 중인 유저: {}", principal);
            redisDao.deleteRefreshToken(principal);
            log.info("블랙리스트에 추가될 토큰: {}", targetAccessToken);
            redisDao.setBlackList(targetAccessToken,"logout", expiration - new Date().getTime());
        }
    }

    /*Principal 추출*/
    public String getPrincipal(String requestAccessToken) {
        return jwtProvider.getAuthentication(requestAccessToken).getName();
    }


    public String getAccessTokenInHeader(String accessTokenInHeader){
        if(accessTokenInHeader != null && accessTokenInHeader.startsWith("Bearer ")) {
            return accessTokenInHeader.substring(subStringNum);
        }
        return null;
    }



}
